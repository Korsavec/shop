package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.seller.person.SellerPerson;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_seller_person")
public class NotActivatedSellerPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = -1301867275762259933L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_seller_person")
    @SequenceGenerator(name = "seq_not_activated_seller_person", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_seller_person")
    private Instant dateDeletionSellerPerson;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;


    // Ссылка на сущность пользователя User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_person_id")
    private SellerPerson sellerPerson;


    public NotActivatedSellerPerson() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionSellerPerson() {
        return dateDeletionSellerPerson;
    }

    public void setDateDeletionSellerPerson(Instant dateDeletionSellerPerson) {
        this.dateDeletionSellerPerson = dateDeletionSellerPerson;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SellerPerson getSellerPerson() {
        return sellerPerson;
    }

    public void setSellerPerson(SellerPerson sellerPerson) {
        this.sellerPerson = sellerPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedSellerPerson that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionSellerPerson().equals(that.getDateDeletionSellerPerson())) return false;
        return getSellerPerson().equals(that.getSellerPerson());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionSellerPerson().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getSellerPerson().hashCode();
        return result;
    }
}
