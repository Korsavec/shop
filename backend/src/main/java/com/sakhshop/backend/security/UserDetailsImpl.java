package com.sakhshop.backend.security;

import com.sakhshop.backend.models.admin.Admin;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import com.sakhshop.backend.models.seller.Seller;
import com.sakhshop.backend.models.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails, Serializable {

  @Serial
  private static final long serialVersionUID = -7738653823823379657L;

  private final Long id;

  private String email;

  private String password;

  private final boolean isAccountNonLocked;

  private final boolean isEnabled;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String email, String password, boolean isAccountNonLocked, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isEnabled = isEnabled;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {

    List<SimpleGrantedAuthority> authorities = user.getRoleUsers().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name())).toList();

    return new UserDetailsImpl(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.isAccountNonLocked(),
            user.isEnabled(),
            authorities);

  }

  public static UserDetailsImpl build(Seller seller) {

    List<SimpleGrantedAuthority> authorities = seller.getSellerRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name())).toList();

    return new UserDetailsImpl(
            seller.getId(),
            seller.getEmail(),
            seller.getPassword(),
            seller.isAccountNonLocked(),
            seller.isEnabled(),
            authorities);

  }


  public static UserDetailsImpl build(Admin admin) {

    List<SimpleGrantedAuthority> authorities = admin.getAdminRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name())).toList();

    return new UserDetailsImpl(
            admin.getId(),
            admin.getEmail(),
            admin.getPassword(),
            admin.isAccountNonLocked(),
            admin.isEnabled(),
            authorities);

  }

  public static UserDetailsImpl build(LogisticsCompany logisticsCompany) {

    List<SimpleGrantedAuthority> authorities = logisticsCompany.getLogisticsCompanyRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name())).toList();

    return new UserDetailsImpl(
            logisticsCompany.getId(),
            logisticsCompany.getEmail(),
            logisticsCompany.getPassword(),
            logisticsCompany.isAccountNonLocked(),
            logisticsCompany.isEnabled(),
            authorities);

  }

  public static UserDetailsImpl build(LogisticsPerson logisticsPerson) {

    List<SimpleGrantedAuthority> authorities = logisticsPerson.getLogisticsPersonRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name())).toList();

    return new UserDetailsImpl(
            logisticsPerson.getId(),
            logisticsPerson.getEmail(),
            logisticsPerson.getPassword(),
            logisticsPerson.isAccountNonLocked(),
            logisticsPerson.isEnabled(),
            authorities);

  }


  // Возвращаю простой объект
  public static UserDetailsImpl buildEmpty() {

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    return new UserDetailsImpl(
            0L,
            "",
            "",
            false,
            false,
            authorities);

  }







  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // Установите значение true, если учетная запись не заблокирована
  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // Установите значение true, если пользователь включен
  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  public Long getId() {
    return id;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserDetailsImpl that)) return false;

    if (isAccountNonLocked() != that.isAccountNonLocked()) return false;
    if (isEnabled() != that.isEnabled()) return false;
    if (!getId().equals(that.getId())) return false;
    if (!email.equals(that.email)) return false;
    if (!getPassword().equals(that.getPassword())) return false;
    return getAuthorities().equals(that.getAuthorities());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + (isAccountNonLocked() ? 1 : 0);
    result = 31 * result + (isEnabled() ? 1 : 0);
    result = 31 * result + getAuthorities().hashCode();
    return result;
  }
}
