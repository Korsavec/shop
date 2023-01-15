package com.sakhshop.backend.models.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.seller.ie.SellerIndividual;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role_seller_individual")
public class RoleSellerIndividual implements Serializable {

    @Serial
    private static final long serialVersionUID = 5046700309138944684L;

    // Это ID Роле
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_seller_individual")
    @SequenceGenerator(name = "seq_role_seller_individual", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Ссылка на сущность пользователя Individual
    @ManyToMany(mappedBy = "roleSellerIndividuals", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<SellerIndividual> sellerIndividuals = new LinkedHashSet<>();



    // Это Enum перечисление ролей.
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 45, insertable = false, updatable = false)
    private RoleEnum roleEnum;

    public RoleSellerIndividual() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SellerIndividual> getSellerIndividuals() {
        return sellerIndividuals;
    }

    public void setSellerIndividuals(Set<SellerIndividual> sellerIndividuals) {
        this.sellerIndividuals = sellerIndividuals;
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
        if (!(o instanceof RoleSellerIndividual that)) return false;

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
