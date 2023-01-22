package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.seller.Seller;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_seller")
public class NotActivatedSeller implements Serializable {

    @Serial
    private static final long serialVersionUID = -1301867275762259933L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_seller")
    @SequenceGenerator(name = "seq_not_activated_seller", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_seller")
    private Instant dateDeletionSeller;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;


    // Ссылка на сущность пользователя User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionSeller() {
        return dateDeletionSeller;
    }

    public void setDateDeletionSeller(Instant dateDeletionSeller) {
        this.dateDeletionSeller = dateDeletionSeller;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedSeller that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionSeller().equals(that.getDateDeletionSeller())) return false;
        return getSeller().equals(that.getSeller());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionSeller().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getSeller().hashCode();
        return result;
    }
}
