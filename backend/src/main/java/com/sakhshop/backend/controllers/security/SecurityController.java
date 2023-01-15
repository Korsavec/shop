
package com.sakhshop.backend.controllers.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serial;
import java.io.Serializable;

@RestController
@RequestMapping("/api/AccountGuard")
public class SecurityController implements Serializable {

  @Serial
  private static final long serialVersionUID = -836681115115289669L;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping(value = "/user", produces = "application/json")
  @PreAuthorize("hasRole('USER')")
  public String userAccess() {
    return "{\"USER\":\"USER текст\" } ";
  }

  @GetMapping(value = "/seller", produces = "application/json")
  @PreAuthorize("hasRole('SELLER_PERSON')")
  public String sellerAccess() {
    return "{\"PERSON\":\"PERSON текст\" } ";
  }

//  @GetMapping("/seller")
//  @PreAuthorize("hasRole('USER') or hasRole('SELLER_INDIVIDUAL')")
//  public String sellerAccess() {
//    return "{\"SELLER\":\"Board\" } ";
//  }
//
//  @GetMapping("/working")
//  @PreAuthorize("hasRole('USER') or hasRole('SELLER_LIMITED')")
//  public String workingAccess() {
//    return "WORKING Board.";
//  }


}
