package com.sakhshop.backend.security;

import com.sakhshop.backend.models.admin.Admin;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import com.sakhshop.backend.models.seller.Seller;
import com.sakhshop.backend.models.user.User;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sakhshop.backend.security.HttpPath.*;

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

      User user = serviceJpa.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(user);

    } else if (request.getRequestURI().equals(API_SINGLETON_LOGIN_SELLER) || request.getRequestURI().equals(API_SINGLETON_GUARD_SELLER)) {

      Seller seller = serviceJpa.findSellerByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(seller);

    } else if (request.getRequestURI().equals(API_SINGLETON_LOGIN_ADMIN) || request.getRequestURI().equals(API_SINGLETON_GUARD_ADMIN)) {

      Admin admin = serviceJpa.findAdminByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(admin);

    } else if (request.getRequestURI().equals(API_SINGLETON_LOGIN_LOGISTICS_COMPANY) || request.getRequestURI().equals(API_SINGLETON_GUARD_LOGISTICS_COMPANY)) {

      LogisticsCompany logisticsCompany = serviceJpa.findLogisticsCompanyByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));

      return UserDetailsImpl.build(logisticsCompany);

    } else if (request.getRequestURI().equals(API_SINGLETON_LOGIN_LOGISTICS_PERSON) || request.getRequestURI().equals(API_SINGLETON_GUARD_LOGISTICS_PERSON)) {

      LogisticsPerson logisticsPerson = serviceJpa.findLogisticsPersonByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));

      return UserDetailsImpl.build(logisticsPerson);

    } else {

      return UserDetailsImpl.buildEmpty();

    }




  }


}
