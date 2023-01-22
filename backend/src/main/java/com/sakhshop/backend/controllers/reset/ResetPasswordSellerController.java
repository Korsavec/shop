package com.sakhshop.backend.controllers.reset;

import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.payload.request.seller.ResetSellerRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.models.seller.Seller;
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
public class ResetPasswordSellerController {

    @Value("${sakhshop.app.main.site.domain.seller}")
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

    public ResetPasswordSellerController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
        this.sendEmail = sendEmail;
        this.validationRegExp = validationRegExp;
        this.limitLogin = limitLogin;
        this.encoder = encoder;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/resetPasswordSeller", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetSellerRequest resetSellerRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerRequest.email() != null
                && !resetSellerRequest.email().equals("")
                && resetSellerRequest.email().length() >= 8
                && resetSellerRequest.email().length() <= 58
                && !validationRegExp.emailValidationRegExp(resetSellerRequest.email())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && Boolean.TRUE.equals(serviceJpa.existsByEmailSeller(resetSellerRequest.email()))) {

            // Создаём token для ссылки
            String token = UUID.randomUUID().toString();


            serviceJpa.updateTokenByEmailSeller(token, resetSellerRequest.email());

            String urlResetPasswordSeller = siteDomain + "/resetPassword/newPassword/" + token;
            sendEmail.resetPasswordSeller(request.getServerName(), urlResetPasswordSeller);

        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


    @PostMapping(value = "/checkTokenSellerResetPassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetSellerRequest resetSellerRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerRequest.token() == null
                || resetSellerRequest.token().equals("")
                || resetSellerRequest.token().length() != 36
                || limitLogin.isBlocked(request.getRemoteAddr())
                || validationRegExp.validationTokenRegExp(resetSellerRequest.token())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

        } else {

            Seller seller = serviceJpa.findSellerByToken(resetSellerRequest.token()).orElse(new Seller());

            if (seller.getEmail() == null || seller.getEmail().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
            } else {


                // Проверяем, подтвердил ли пользователь свой email адрес. Если ip адрес есть, значит подтвердил.
                // Если адрес не подтверждён, то подтверждаем и активируем.
                if (seller.getIpAddressRegConfirm() == null || seller.getIpAddressRegConfirm().isEmpty()) {

                    seller.setEnabled(true);
                    seller.setIpAddressRegConfirm(request.getRemoteAddr());

                    NotActivatedSeller notActivatedSeller = seller.getNotActivatedSeller();
                    notActivatedSeller.setActive(true);
                    seller.setNotActivatedSeller(notActivatedSeller);

                    serviceJpa.saveSeller(seller);

                }


                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


            }

        }


    }


    @PostMapping(value = "/newPasswordSeller", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetSellerRequest resetSellerRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerRequest.password() != null
                && !resetSellerRequest.password().equals("")
                && resetSellerRequest.password().length() >= 6
                && resetSellerRequest.password().length() <= 24
                && !validationRegExp.passwordValidationRegExp(resetSellerRequest.password())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && !validationRegExp.validationTokenRegExp(resetSellerRequest.token())) {

            Seller seller = serviceJpa.findSellerByToken(resetSellerRequest.token()).orElse(new Seller());

            if (seller.getToken() != null && !seller.getToken().isEmpty()) {

                serviceJpa.updatePasswordTokenByEmailSeller(null, encoder.encode(resetSellerRequest.password()), seller.getEmail());
            }


        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }

}
