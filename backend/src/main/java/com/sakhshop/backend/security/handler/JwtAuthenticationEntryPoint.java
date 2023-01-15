package com.sakhshop.backend.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakhshop.backend.models.exception.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class JwtAuthenticationEntryPoint implements Serializable, AuthenticationEntryPoint {

  @Serial
  private static final long serialVersionUID = -5383101233587821186L;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
          throws IOException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    HttpResponse httpResponse = new HttpResponse(
            new Date(),
            FORBIDDEN.value(),
            FORBIDDEN,
            FORBIDDEN.getReasonPhrase().toUpperCase(),
            "Вам необходимо войти в систему, чтобы получить доступ к этой странице");

    final ObjectMapper mapper = new ObjectMapper();

    mapper.writeValue(response.getOutputStream(), httpResponse);

  }

}
