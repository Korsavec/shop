package com.sakhshop.backend.models.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.seller.llc.SellerLimited;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role_seller_limited")
public class RoleSellerLimited implements Serializable {

    // Это ID Роле
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_seller_limited")
    @SequenceGenerator(name = "seq_role_seller_limited", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Ссылка на сущность пользователя SellerIndividual
    @ManyToMany(mappedBy = "roleSellerLimiteds", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<SellerLimited> sellerLimiteds = new LinkedHashSet<>();





    // Это Enum перечисление ролей.
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 45, insertable = false, updatable = false)
    private RoleEnum roleEnum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SellerLimited> getSellerLimiteds() {
        return sellerLimiteds;
    }

    public void setSellerLimiteds(Set<SellerLimited> sellerLimiteds) {
        this.sellerLimiteds = sellerLimiteds;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleSellerLimited that)) return false;

        if (!getId().equals(that.getId())) return false;
        return getRoleEnum() == that.getRoleEnum();
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getRoleEnum().hashCode();
        return result;
    }
}
