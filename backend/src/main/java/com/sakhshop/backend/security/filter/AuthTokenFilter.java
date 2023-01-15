package com.sakhshop.backend.security.filter;

import com.sakhshop.backend.security.UserDetailsServiceImpl;
import com.sakhshop.backend.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class AuthTokenFilter extends OncePerRequestFilter implements Serializable {

  @Serial
  private static final long serialVersionUID = -832736735224971668L;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      String jwt = parseJwt(request);

    try {
      if (!jwt.equals("null") && !jwtUtils.validateJwtToken(jwt).getBody().get("email").toString().isEmpty()) {

        String email = jwtUtils.getUserEmailFromJwtToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

      }
    } catch (Exception e) {
      throw new UsernameNotFoundException("no");
    }

    filterChain.doFilter(request, response);



  }


  private String parseJwt(HttpServletRequest request) {


    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {

      return headerAuth.substring(7);
    }

    return "null";
  }


}
