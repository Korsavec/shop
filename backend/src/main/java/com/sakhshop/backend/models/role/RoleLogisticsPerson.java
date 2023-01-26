package com.sakhshop.backend.models.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role_logistics_person")
public class RoleLogisticsPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = -2053629011004325301L;

    // Это ID Роле
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_logistics_person")
    @SequenceGenerator(name = "seq_role_logistics_person", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;





    // Ссылка на сущность пользователя LogisticsPerson
    @ManyToMany(mappedBy = "logisticsPersonRoles", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<LogisticsPerson> logisticsPersons = new LinkedHashSet<>();

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

    public Set<LogisticsPerson> getLogisticsPersons() {
        return logisticsPersons;
    }

    public void setLogisticsPersons(Set<LogisticsPerson> logisticsPersons) {
        this.logisticsPersons = logisticsPersons;
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
        if (!(o instanceof RoleLogisticsPerson that)) return false;

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
