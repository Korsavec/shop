
package com.sakhshop.backend.controllers.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AccountGuard")
public class SecurityController {


  @GetMapping("/all")
  public String allAccess() {
    return "Public Content all.";
  }

  @GetMapping(value = "/user", produces = "application/json")
  @PreAuthorize("hasRole('USER')")
  public String userAccess() {
    return "{\"USER\":\"USER текст\" } ";
  }

  @GetMapping(value = "/seller", produces = "application/json")
  @PreAuthorize("hasRole('SELLER')")
  public String sellerAccess() {
    return "{\"PERSON\":\"SELLER текст\" } ";
  }

  @GetMapping(value = "/admin", produces = "application/json")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "{\"PERSON\":\"ADMIN текст\" } ";
  }

  @GetMapping(value = "/logisticsCompany", produces = "application/json")
  @PreAuthorize("hasRole('LOGISTICS_COMPANY')")
  public String logisticsCompanyAccess() {
    return "{\"PERSON\":\"ADMIN текст\" } ";
  }


}
