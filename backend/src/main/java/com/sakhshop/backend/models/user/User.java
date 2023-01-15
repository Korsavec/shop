package com.sakhshop.backend.models.user;

import com.sakhshop.backend.models.activation.NotActivatedUser;
import com.sakhshop.backend.models.product.Product;
import com.sakhshop.backend.models.role.RoleUser;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_users")
public class User implements Serializable {

  @Serial
  private static final long serialVersionUID = -7495737734488450424L;

  // Это ID пользователя
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_user")
  @SequenceGenerator(name = "seq_a_user", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  // Это номер телефона пользователя
  @NaturalId
  @Column(name = "phone", unique = true, length = 15)
  private Long phone;

  // Это email пользователя
  @NaturalId
  @Column(name = "email", nullable = false, unique = true, length = 58)
  private String email;

  // Это пароль пользователя
  @Column(name = "password", nullable = false, length = 65)
  private String password;

  // Это псевдоним пользователя
  @Column(name = "username", unique = true, length = 25)
  private String username;


  // Это настоящая фамилия пользователя
  @Column(name = "surname", length = 45)
  private String surname;

  // Это настоящее имя пользователя
  @Column(name = "name", length = 45)
  private String name;

  // Это настоящее отчество пользователя
  @Column(name = "middle_name", length = 45)
  private String middleName;

  // Это дата рождения пользователя
  @Column(name = "date_birth")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private LocalDate dateBirth;

  // Установите значение true, если пользователь включен
  @Column(name = "enabled", nullable = false, length = 1)
  private boolean enabled;

  // Token для подтверждения email адреса и сброса пароля
  @Column(name = "token", unique = true, length = 45)
  private String token;

  // Установите значение true, если учетная запись не заблокирована
  @Column(name = "account_non_locked", nullable = false, length = 1)
  private boolean accountNonLocked;

  // Это дата создания учётной записи пользователя
  @Column(name = "date_created_user", nullable = false)
  private Instant dateCreatedUser;

  // Это ip адрес с которого была зарегистрирована учётная запись пользователя
  @Column(name = "ip_address_registration", nullable = false, length = 39)
  private String ipAddressRegistration;

  // Это ip адрес с которого был подтверждён адрес электронной почты пользователя
  @Column(name = "ip_address_reg_confirm", length = 39)
  private String ipAddressRegConfirm;

  // Это ip адрес с которого был осуществлён первый вход в учётную запись пользователя
  @Column(name = "ip_address_first_entrance", length = 39)
  private String ipAddressFirstEntrance;

  // TODO необходимо реализовать, чтобы сохранять IP адрес последнего входа
  // Это ip адрес с которого был осуществлён последний вход в учётную запись пользователя
  @Column(name = "ip_address_last_entrance", length = 39)
  private String ipAddressLastEntrance;


  // Это таблица связей ManyToMany User и RoleUser
  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(name = "join_a_user_and_role_user",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_users_id", referencedColumnName = "id"))
  private Set<RoleUser> roleUsers = new LinkedHashSet<>();


  // Ссылка на сущность товара
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.PERSIST)
  private transient Set<Product> products = new LinkedHashSet<>();


  // Сущность активации аккаунта
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private transient NotActivatedUser notActivatedUser;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public LocalDate getDateBirth() {
    return dateBirth;
  }

  public void setDateBirth(LocalDate dateBirth) {
    this.dateBirth = dateBirth;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public Instant getDateCreatedUser() {
    return dateCreatedUser;
  }

  public void setDateCreatedUser(Instant dateCreatedUser) {
    this.dateCreatedUser = dateCreatedUser;
  }

  public String getIpAddressRegistration() {
    return ipAddressRegistration;
  }

  public void setIpAddressRegistration(String ipAddressRegistration) {
    this.ipAddressRegistration = ipAddressRegistration;
  }

  public String getIpAddressRegConfirm() {
    return ipAddressRegConfirm;
  }

  public void setIpAddressRegConfirm(String ipAddressRegConfirm) {
    this.ipAddressRegConfirm = ipAddressRegConfirm;
  }

  public String getIpAddressFirstEntrance() {
    return ipAddressFirstEntrance;
  }

  public void setIpAddressFirstEntrance(String ipAddressFirstEntrance) {
    this.ipAddressFirstEntrance = ipAddressFirstEntrance;
  }

  public String getIpAddressLastEntrance() {
    return ipAddressLastEntrance;
  }

  public void setIpAddressLastEntrance(String ipAddressLastEntrance) {
    this.ipAddressLastEntrance = ipAddressLastEntrance;
  }

  public Set<RoleUser> getRoleUsers() {
    return roleUsers;
  }

  public void setRoleUsers(Set<RoleUser> roleUsers) {
    this.roleUsers = roleUsers;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  public NotActivatedUser getNotActivatedUser() {
    return notActivatedUser;
  }

  public void setNotActivatedUser(NotActivatedUser notActivatedUser) {
    this.notActivatedUser = notActivatedUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;

    if (isEnabled() != user.isEnabled()) return false;
    if (isAccountNonLocked() != user.isAccountNonLocked()) return false;
    if (!getId().equals(user.getId())) return false;
    if (!getPhone().equals(user.getPhone())) return false;
    if (!getEmail().equals(user.getEmail())) return false;
    if (!getPassword().equals(user.getPassword())) return false;
    if (!getUsername().equals(user.getUsername())) return false;
    if (!getSurname().equals(user.getSurname())) return false;
    if (!getName().equals(user.getName())) return false;
    if (!getMiddleName().equals(user.getMiddleName())) return false;
    if (!getDateBirth().equals(user.getDateBirth())) return false;
    if (!getToken().equals(user.getToken())) return false;
    if (!getDateCreatedUser().equals(user.getDateCreatedUser())) return false;
    if (!getIpAddressRegistration().equals(user.getIpAddressRegistration())) return false;
    if (!getIpAddressRegConfirm().equals(user.getIpAddressRegConfirm())) return false;
    if (!getIpAddressFirstEntrance().equals(user.getIpAddressFirstEntrance())) return false;
    if (!getIpAddressLastEntrance().equals(user.getIpAddressLastEntrance())) return false;
    if (!getRoleUsers().equals(user.getRoleUsers())) return false;
    if (!getProducts().equals(user.getProducts())) return false;
    return getNotActivatedUser().equals(user.getNotActivatedUser());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getPhone().hashCode();
    result = 31 * result + getEmail().hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + getUsername().hashCode();
    result = 31 * result + getSurname().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getMiddleName().hashCode();
    result = 31 * result + getDateBirth().hashCode();
    result = 31 * result + (isEnabled() ? 1 : 0);
    result = 31 * result + getToken().hashCode();
    result = 31 * result + (isAccountNonLocked() ? 1 : 0);
    result = 31 * result + getDateCreatedUser().hashCode();
    result = 31 * result + getIpAddressRegistration().hashCode();
    result = 31 * result + getIpAddressRegConfirm().hashCode();
    result = 31 * result + getIpAddressFirstEntrance().hashCode();
    result = 31 * result + getIpAddressLastEntrance().hashCode();
    result = 31 * result + getRoleUsers().hashCode();
    result = 31 * result + getProducts().hashCode();
    result = 31 * result + getNotActivatedUser().hashCode();
    return result;
  }
}
