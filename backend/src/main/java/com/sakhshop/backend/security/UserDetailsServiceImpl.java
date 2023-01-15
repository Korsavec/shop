package com.sakhshop.backend.security;

import com.sakhshop.backend.models.seller.person.SellerPerson;
import com.sakhshop.backend.models.user.User;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;

import static com.sakhshop.backend.security.HttpPath.API_SINGLETON_GUARD_USER_PATH;
import static com.sakhshop.backend.security.HttpPath.API_SINGLETON_LOGIN_USER_PATH;

@Transactional
@Service
public class UserDetailsServiceImpl implements Serializable, UserDetailsService {

  @Serial
  private static final long serialVersionUID = -3588662209041923365L;

  private final
  ServiceJpa serviceJpa;

  public final
  HttpServletRequest request;

  public UserDetailsServiceImpl(ServiceJpa serviceJpa, HttpServletRequest request) {
    this.serviceJpa = serviceJpa;
    this.request = request;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    if (request.getRequestURI().equals(API_SINGLETON_LOGIN_USER_PATH) || request.getRequestURI().equals(API_SINGLETON_GUARD_USER_PATH)) {

      User user = serviceJpa.findUserByEmailUser(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(user);

    } else {

      SellerPerson sellerPerson = serviceJpa.findSellerPersonByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(sellerPerson);

    }




  }


}
