package com.sakhshop.backend.listner;

import com.sakhshop.backend.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class AuthenticationSuccessListener implements Serializable {

    @Serial
    private static final long serialVersionUID = -7375381351881182694L;

    private final
    LoginAttemptService loginAttemptService;

    private final
    HttpServletRequest request;

    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }


    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent ignoredEvent) {

        final String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null) {

            loginAttemptService.deleteCache(request.getRemoteAddr());

        } else {

            loginAttemptService.deleteCache(xfHeader.split(",")[0]);

        }

    }

}
