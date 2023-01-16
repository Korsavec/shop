package com.sakhshop.backend.controllers.login.seller.person;

import com.sakhshop.backend.models.payload.request.seller.person.LoginSellerPersonRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.security.jwt.JwtUtils;
import com.sakhshop.backend.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SellerPersonController {

    private final
    AuthenticationManager authenticationManager;

    private final
    JwtUtils jwtUtils;

    private final
    LoginAttemptService limitLogin;

    public SellerPersonController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, LoginAttemptService limitLogin) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.limitLogin = limitLogin;
    }

    @PostMapping(value = "/loginSellerPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> authenticateUser(@RequestBody LoginSellerPersonRequest loginSellerPersonRequest, HttpServletRequest request) {



        if (limitLogin.isBlocked(request.getRemoteAddr())) {

            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "Что-то пошло не так. Попробуйте позе"),
                    HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginSellerPersonRequest.email(), loginSellerPersonRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication, loginSellerPersonRequest.checkbox());

            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), jwt),
                    HttpStatus.OK);

        }

    }

}
