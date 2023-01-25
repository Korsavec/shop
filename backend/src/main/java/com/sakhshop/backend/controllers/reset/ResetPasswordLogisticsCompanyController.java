package com.sakhshop.backend.controllers.reset;

import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.models.activation.NotActivatedLogisticsCompany;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.models.payload.request.logistics.company.ResetLogisticsCompanyRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.service.cache.LoginAttemptService;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import com.sakhshop.backend.validation.ValidationRegExp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sakhshop.backend.config.Constants.STATIC_NO;
import static com.sakhshop.backend.config.Constants.STATIC_OK;

@RestController
@RequestMapping("/api/auth")
public class ResetPasswordLogisticsCompanyController {


    @Value("${sakhshop.app.main.site.domain.logistics.company}")
    String siteDomain;

    public final
    SendEmail sendEmail;

    public final
    ValidationRegExp validationRegExp;

    public final
    LoginAttemptService limitLogin;

    public final
    PasswordEncoder encoder;

    private final
    ServiceJpa serviceJpa;

    public ResetPasswordLogisticsCompanyController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
        this.sendEmail = sendEmail;
        this.validationRegExp = validationRegExp;
        this.limitLogin = limitLogin;
        this.encoder = encoder;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/resetPasswordLogisticsCompany", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetLogisticsCompanyRequest resetLogisticsCompanyRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsCompanyRequest.email() != null
                && !resetLogisticsCompanyRequest.email().equals("")
                && resetLogisticsCompanyRequest.email().length() >= 8
                && resetLogisticsCompanyRequest.email().length() <= 58
                && !validationRegExp.emailValidationRegExp(resetLogisticsCompanyRequest.email())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && Boolean.TRUE.equals(serviceJpa.existsLogisticsCompanyByEmail(resetLogisticsCompanyRequest.email()))) {

            // Создаём token для ссылки
            String token = UUID.randomUUID().toString();


            serviceJpa.updateLogisticsCompanyTokenByEmail(token, resetLogisticsCompanyRequest.email());

            String urlResetPasswordLogisticsCompany = siteDomain + "/resetPassword/newPassword/" + token;
            sendEmail.resetPasswordLogisticsCompany(request.getServerName(), urlResetPasswordLogisticsCompany);

        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


    @PostMapping(value = "/checkTokenLogisticsCompanyResetPassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetLogisticsCompanyRequest resetLogisticsCompanyRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsCompanyRequest.token() == null
                || resetLogisticsCompanyRequest.token().equals("")
                || resetLogisticsCompanyRequest.token().length() != 36
                || limitLogin.isBlocked(request.getRemoteAddr())
                || validationRegExp.validationTokenRegExp(resetLogisticsCompanyRequest.token())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

        } else {

            LogisticsCompany logisticsCompany = serviceJpa.findLogisticsCompanyByToken(resetLogisticsCompanyRequest.token()).orElse(new LogisticsCompany());

            if (logisticsCompany.getEmail() == null || logisticsCompany.getEmail().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
            } else {


                // Проверяем, подтвердил ли пользователь свой email адрес. Если ip адрес есть, значит подтвердил.
                // Если адрес не подтверждён, то подтверждаем и активируем.
                if (logisticsCompany.getIpAddressRegConfirm() == null || logisticsCompany.getIpAddressRegConfirm().isEmpty()) {

                    logisticsCompany.setEnabled(true);
                    logisticsCompany.setIpAddressRegConfirm(request.getRemoteAddr());

                    NotActivatedLogisticsCompany notActivatedLogisticsCompany = logisticsCompany.getNotActivatedLogisticsCompany();
                    notActivatedLogisticsCompany.setActive(true);
                    logisticsCompany.setNotActivatedLogisticsCompany(notActivatedLogisticsCompany);

                    serviceJpa.saveLogisticsCompany(logisticsCompany);

                }


                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


            }

        }


    }


    @PostMapping(value = "/newPasswordLogisticsCompany", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetLogisticsCompanyRequest resetLogisticsCompanyRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsCompanyRequest.password() != null
                && !resetLogisticsCompanyRequest.password().equals("")
                && resetLogisticsCompanyRequest.password().length() >= 6
                && resetLogisticsCompanyRequest.password().length() <= 24
                && !validationRegExp.passwordValidationRegExp(resetLogisticsCompanyRequest.password())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && !validationRegExp.validationTokenRegExp(resetLogisticsCompanyRequest.token())) {

            LogisticsCompany logisticsCompany = serviceJpa.findLogisticsCompanyByToken(resetLogisticsCompanyRequest.token()).orElse(new LogisticsCompany());

            if (logisticsCompany.getToken() != null && !logisticsCompany.getToken().isEmpty()) {

                serviceJpa.updateLogisticsCompanyPasswordTokenByEmail(null, encoder.encode(resetLogisticsCompanyRequest.password()), logisticsCompany.getEmail());
            }


        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


}
