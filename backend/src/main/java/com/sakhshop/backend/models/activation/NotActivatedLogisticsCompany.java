package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_logistics_company")
public class NotActivatedLogisticsCompany implements Serializable {

    @Serial
    private static final long serialVersionUID = 5887987605624380818L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_logistics_company")
    @SequenceGenerator(name = "seq_not_activated_logistics_company", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_logistics_company")
    private Instant dateDeletionLogisticsCompany;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;


    // Ссылка на сущность LogisticsCompany
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistics_company_id")
    private LogisticsCompany logisticsCompany;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionLogisticsCompany() {
        return dateDeletionLogisticsCompany;
    }

    public void setDateDeletionLogisticsCompany(Instant dateDeletionLogisticsCompany) {
        this.dateDeletionLogisticsCompany = dateDeletionLogisticsCompany;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LogisticsCompany getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(LogisticsCompany logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedLogisticsCompany that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionLogisticsCompany().equals(that.getDateDeletionLogisticsCompany())) return false;
        return getLogisticsCompany().equals(that.getLogisticsCompany());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionLogisticsCompany().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getLogisticsCompany().hashCode();
        return result;
    }
}
