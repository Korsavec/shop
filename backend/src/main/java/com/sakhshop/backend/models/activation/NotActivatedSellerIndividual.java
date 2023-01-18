package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.seller.ie.SellerIndividual;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_seller_individual")
public class NotActivatedSellerIndividual implements Serializable {

    @Serial
    private static final long serialVersionUID = 286858327683597343L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_seller_individual")
    @SequenceGenerator(name = "seq_not_activated_seller_individual", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_seller_individual")
    private Instant dateDeletionSellerIndividual;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    // Ссылка на сущность пользователя SellerIndividual
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_individual_id")
    private SellerIndividual sellerIndividual;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionSellerIndividual() {
        return dateDeletionSellerIndividual;
    }

    public void setDateDeletionSellerIndividual(Instant dateDeletionSellerIndividual) {
        this.dateDeletionSellerIndividual = dateDeletionSellerIndividual;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SellerIndividual getSellerIndividual() {
        return sellerIndividual;
    }

    public void setSellerIndividual(SellerIndividual sellerIndividual) {
        this.sellerIndividual = sellerIndividual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedSellerIndividual that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionSellerIndividual().equals(that.getDateDeletionSellerIndividual())) return false;
        return getSellerIndividual().equals(that.getSellerIndividual());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionSellerIndividual().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getSellerIndividual().hashCode();
        return result;
    }
}
