package com.sakhshop.backend.security;

import com.sakhshop.backend.models.seller.person.SellerPerson;
import com.sakhshop.backend.models.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails, Serializable {

  @Serial
  private static final long serialVersionUID = -5637289018724145163L;

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

    List<GrantedAuthority> authorities = user.getRoleUsers().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.isAccountNonLocked(),
        user.isEnabled(),
        authorities);

  }

  public static UserDetailsImpl build(SellerPerson sellerPerson) {

    List<GrantedAuthority> authorities = sellerPerson.getRoleSellerPersons().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleEnum().name()))
            .collect(Collectors.toList());

    return new UserDetailsImpl(
            sellerPerson.getId(),
            sellerPerson.getEmail(),
            sellerPerson.getPassword(),
            sellerPerson.isAccountNonLocked(),
            sellerPerson.isEnabled(),
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

  public String getEmail() {
    return email;
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
    if (!getEmail().equals(that.getEmail())) return false;
    if (!getPassword().equals(that.getPassword())) return false;
    return getAuthorities().equals(that.getAuthorities());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getEmail().hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + (isAccountNonLocked() ? 1 : 0);
    result = 31 * result + (isEnabled() ? 1 : 0);
    result = 31 * result + getAuthorities().hashCode();
    return result;
  }
}
