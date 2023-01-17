package com.sakhshop.backend.controllers.reset;

import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.models.activation.NotActivatedSellerPerson;
import com.sakhshop.backend.models.payload.request.seller.person.ResetSellerPersonRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.models.seller.person.SellerPerson;
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
public class ResetPasswordSellerPersonController {

    @Value("${sakhshop.backend.app.site.domain}")
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

    public ResetPasswordSellerPersonController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
        this.sendEmail = sendEmail;
        this.validationRegExp = validationRegExp;
        this.limitLogin = limitLogin;
        this.encoder = encoder;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/resetPasswordSellerPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetSellerPersonRequest resetSellerPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerPersonRequest.email() != null
                && !resetSellerPersonRequest.email().equals("")
                && resetSellerPersonRequest.email().length() >= 8
                && resetSellerPersonRequest.email().length() <= 58
                && !validationRegExp.emailValidationRegExp(resetSellerPersonRequest.email())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && Boolean.TRUE.equals(serviceJpa.existsByEmailSellerPerson(resetSellerPersonRequest.email()))) {

            // Создаём token для ссылки
            String token = UUID.randomUUID().toString();


            serviceJpa.updateTokenByEmailSellerPerson(token, resetSellerPersonRequest.email());

            String urlResetPasswordUserAccount = siteDomain + "/resetPassword/newPasswordSellerPerson/" + token;
            sendEmail.resetPasswordUserAccount(request.getServerName(), urlResetPasswordUserAccount);

        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


    @PostMapping(value = "/checkServerTokenSellerPersonResetPassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetSellerPersonRequest resetSellerPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerPersonRequest.token() == null
                || resetSellerPersonRequest.token().equals("")
                || resetSellerPersonRequest.token().length() != 36
                || limitLogin.isBlocked(request.getRemoteAddr())
                || validationRegExp.validationTokenRegExp(resetSellerPersonRequest.token())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

        } else {

            SellerPerson sellerPerson = serviceJpa.findSellerPersonByToken(resetSellerPersonRequest.token()).orElse(new SellerPerson());

            if (sellerPerson.getEmail() == null || sellerPerson.getEmail().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
            } else {


                // Проверяем, подтвердил ли пользователь свой email адрес. Если ip адрес есть, значит подтвердил.
                // Если адрес не подтверждён, то подтверждаем и активируем.
                if (sellerPerson.getIpAddressRegConfirm() == null || sellerPerson.getIpAddressRegConfirm().isEmpty()) {

                    sellerPerson.setEnabled(true);
                    sellerPerson.setIpAddressRegConfirm(request.getRemoteAddr());

                    NotActivatedSellerPerson notActivatedSellerPerson = sellerPerson.getNotActivatedSellerPerson();
                    notActivatedSellerPerson.setActive(true);
                    sellerPerson.setNotActivatedSellerPerson(notActivatedSellerPerson);

                    serviceJpa.saveSellerPerson(sellerPerson);

                }


                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


            }

        }


    }


    @PostMapping(value = "/newPasswordSellerPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetSellerPersonRequest resetSellerPersonRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetSellerPersonRequest.password() != null
                && !resetSellerPersonRequest.password().equals("")
                && resetSellerPersonRequest.password().length() >= 6
                && resetSellerPersonRequest.password().length() <= 24
                && !validationRegExp.passwordValidationRegExp(resetSellerPersonRequest.password())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && !validationRegExp.validationTokenRegExp(resetSellerPersonRequest.token())) {

            SellerPerson sellerPerson = serviceJpa.findSellerPersonByToken(resetSellerPersonRequest.token()).orElse(new SellerPerson());

            if (sellerPerson.getToken() != null && !sellerPerson.getToken().isEmpty()) {

                serviceJpa.updatePasswordTokenByEmailSellerPerson(null, encoder.encode(resetSellerPersonRequest.password()), sellerPerson.getEmail());
            }


        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }

}
