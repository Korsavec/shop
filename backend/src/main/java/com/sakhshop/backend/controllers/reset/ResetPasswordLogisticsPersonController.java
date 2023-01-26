package com.sakhshop.backend.controllers.reset;

import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.models.activation.NotActivatedLogisticsPerson;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import com.sakhshop.backend.models.payload.request.logistics.person.ResetLogisticsPersonRequest;
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
public class ResetPasswordLogisticsPersonController {


    @Value("${sakhshop.app.main.site.domain.logistics.person}")
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

    public ResetPasswordLogisticsPersonController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
        this.sendEmail = sendEmail;
        this.validationRegExp = validationRegExp;
        this.limitLogin = limitLogin;
        this.encoder = encoder;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/resetPasswordLogisticsPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetLogisticsPersonRequest resetLogisticsPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsPersonRequest.email() != null
                && !resetLogisticsPersonRequest.email().equals("")
                && resetLogisticsPersonRequest.email().length() >= 8
                && resetLogisticsPersonRequest.email().length() <= 58
                && !validationRegExp.emailValidationRegExp(resetLogisticsPersonRequest.email())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && Boolean.TRUE.equals(serviceJpa.existsLogisticsPersonByEmail(resetLogisticsPersonRequest.email()))) {

            // Создаём token для ссылки
            String token = UUID.randomUUID().toString();


            serviceJpa.updateLogisticsPersonTokenByEmail(token, resetLogisticsPersonRequest.email());

            String urlResetPasswordLogisticsPerson = siteDomain + "/resetPassword/newPassword/" + token;
            sendEmail.resetPasswordLogisticsPerson(request.getServerName(), urlResetPasswordLogisticsPerson);

        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


    @PostMapping(value = "/checkTokenLogisticsPersonResetPassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetLogisticsPersonRequest resetLogisticsPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsPersonRequest.token() == null
                || resetLogisticsPersonRequest.token().equals("")
                || resetLogisticsPersonRequest.token().length() != 36
                || limitLogin.isBlocked(request.getRemoteAddr())
                || validationRegExp.validationTokenRegExp(resetLogisticsPersonRequest.token())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

        } else {

            LogisticsPerson logisticsPerson = serviceJpa.findLogisticsPersonByToken(resetLogisticsPersonRequest.token()).orElse(new LogisticsPerson());

            if (logisticsPerson.getEmail() == null || logisticsPerson.getEmail().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
            } else {


                // Проверяем, подтвердил ли пользователь свой email адрес. Если ip адрес есть, значит подтвердил.
                // Если адрес не подтверждён, то подтверждаем и активируем.
                if (logisticsPerson.getIpAddressRegConfirm() == null || logisticsPerson.getIpAddressRegConfirm().isEmpty()) {

                    logisticsPerson.setEnabled(true);
                    logisticsPerson.setIpAddressRegConfirm(request.getRemoteAddr());

                    NotActivatedLogisticsPerson notActivatedLogisticsPerson = logisticsPerson.getNotActivatedLogisticsPerson();
                    notActivatedLogisticsPerson.setActive(true);
                    logisticsPerson.setNotActivatedLogisticsPerson(notActivatedLogisticsPerson);

                    serviceJpa.saveLogisticsPerson(logisticsPerson);

                }


                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


            }

        }


    }


    @PostMapping(value = "/newPasswordLogisticsPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetLogisticsPersonRequest resetLogisticsPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetLogisticsPersonRequest.password() != null
                && !resetLogisticsPersonRequest.password().equals("")
                && resetLogisticsPersonRequest.password().length() >= 6
                && resetLogisticsPersonRequest.password().length() <= 24
                && !validationRegExp.passwordValidationRegExp(resetLogisticsPersonRequest.password())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && !validationRegExp.validationTokenRegExp(resetLogisticsPersonRequest.token())) {

            LogisticsPerson logisticsPerson = serviceJpa.findLogisticsPersonByToken(resetLogisticsPersonRequest.token()).orElse(new LogisticsPerson());

            if (logisticsPerson.getToken() != null && !logisticsPerson.getToken().isEmpty()) {

                serviceJpa.updateLogisticsPersonPasswordTokenByEmail(null, encoder.encode(resetLogisticsPersonRequest.password()), logisticsPerson.getEmail());
            }


        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }

}
