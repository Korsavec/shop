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

import static com.sakhshop.backend.security.HttpPath.API_SINGLETON_GUARD_USER;
import static com.sakhshop.backend.security.HttpPath.API_SINGLETON_LOGIN_USER;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

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

    if (request.getRequestURI().equals(API_SINGLETON_LOGIN_USER) || request.getRequestURI().equals(API_SINGLETON_GUARD_USER)) {

      User user = serviceJpa.findUserByEmailUser(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(user);

    } else {

      SellerPerson sellerPerson = serviceJpa.findSellerPersonByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(sellerPerson);

    }




  }


}
