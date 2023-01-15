package com.sakhshop.backend.listner;

import com.sakhshop.backend.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class AuthenticationFailureListener implements Serializable {

    @Serial
    private static final long serialVersionUID = -7472388401097077191L;

    private final
    LoginAttemptService loginAttemptService;

    private final
    HttpServletRequest request;

    public AuthenticationFailureListener(LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent ignoredEvent) {

        final String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null) {

            loginAttemptService.addCache(request.getRemoteAddr());

        } else {

            loginAttemptService.addCache(xfHeader.split(",")[0]);

        }

    }

}
