package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.seller.llc.SellerLimited;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_seller_limited")
public class NotActivatedSellerLimited implements Serializable {

    @Serial
    private static final long serialVersionUID = 3312280629316152441L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_seller_limited")
    @SequenceGenerator(name = "seq_not_activated_seller_limited", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_seller_limited")
    private Instant dateDeletionSellerLimited;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;


    // Ссылка на сущность пользователя Individual
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_limited_id")
    private SellerLimited sellerLimited;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionSellerLimited() {
        return dateDeletionSellerLimited;
    }

    public void setDateDeletionSellerLimited(Instant dateDeletionSellerLimited) {
        this.dateDeletionSellerLimited = dateDeletionSellerLimited;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SellerLimited getSellerLimited() {
        return sellerLimited;
    }

    public void setSellerLimited(SellerLimited sellerLimited) {
        this.sellerLimited = sellerLimited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedSellerLimited that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionSellerLimited().equals(that.getDateDeletionSellerLimited())) return false;
        return getSellerLimited().equals(that.getSellerLimited());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionSellerLimited().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getSellerLimited().hashCode();
        return result;
    }
}
