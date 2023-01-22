package com.sakhshop.backend.models.admin;

import com.sakhshop.backend.models.role.RoleAdmin;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_admin")
public class Admin implements Serializable {

    @Serial
    private static final long serialVersionUID = -259290452696746086L;

    // Это ID пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_admin")
    @SequenceGenerator(name = "seq_a_admin", allocationSize = 1)
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
    @Column(name = "name", unique = true, length = 25)
    private String name;





    // Установите значение true, если пользователь включен
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;

    // Token для подтверждения email адреса и сброса пароля
    @Column(name = "token", unique = true, length = 45)
    private String token;

    // Установите значение true, если учетная запись не заблокирована
    @Column(name = "account_non_locked", nullable = false, length = 1)
    private boolean accountNonLocked;






    // Это таблица связей ManyToMany PrivatePerson и RoleUser
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_a_admin_and_role_admin",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_admin_id", referencedColumnName = "id"))
    private Set<RoleAdmin> adminRoles = new LinkedHashSet<>();


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<RoleAdmin> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(Set<RoleAdmin> adminRoles) {
        this.adminRoles = adminRoles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;

        if (isEnabled() != admin.isEnabled()) return false;
        if (isAccountNonLocked() != admin.isAccountNonLocked()) return false;
        if (!getId().equals(admin.getId())) return false;
        if (!getPhone().equals(admin.getPhone())) return false;
        if (!getEmail().equals(admin.getEmail())) return false;
        if (!getPassword().equals(admin.getPassword())) return false;
        if (!getName().equals(admin.getName())) return false;
        if (!getToken().equals(admin.getToken())) return false;
        return getAdminRoles().equals(admin.getAdminRoles());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + getToken().hashCode();
        result = 31 * result + (isAccountNonLocked() ? 1 : 0);
        result = 31 * result + getAdminRoles().hashCode();
        return result;
    }
}
