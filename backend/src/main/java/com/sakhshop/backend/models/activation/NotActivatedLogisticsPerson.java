package com.sakhshop.backend.models.activation;

import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "not_activated_logistics_person")
public class NotActivatedLogisticsPerson implements Serializable {


    @Serial
    private static final long serialVersionUID = 9222839126124584835L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_activated_logistics_person")
    @SequenceGenerator(name = "seq_not_activated_logistics_person", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это дата удаления аккаунта с неподтверждённым адресом электронной почты
    @Column(name = "date_deletion_logistics_person")
    private Instant dateDeletionLogisticsPerson;

    // Активирован аккаунт или нет
    @Column(name = "active", nullable = false, length = 1)
    private boolean active;


    // Ссылка на сущность LogisticsPerson
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistics_person_id")
    private LogisticsPerson logisticsPerson;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDeletionLogisticsPerson() {
        return dateDeletionLogisticsPerson;
    }

    public void setDateDeletionLogisticsPerson(Instant dateDeletionLogisticsPerson) {
        this.dateDeletionLogisticsPerson = dateDeletionLogisticsPerson;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LogisticsPerson getLogisticsPerson() {
        return logisticsPerson;
    }

    public void setLogisticsPerson(LogisticsPerson logisticsPerson) {
        this.logisticsPerson = logisticsPerson;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotActivatedLogisticsPerson that)) return false;

        if (isActive() != that.isActive()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDateDeletionLogisticsPerson().equals(that.getDateDeletionLogisticsPerson())) return false;
        return getLogisticsPerson().equals(that.getLogisticsPerson());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDateDeletionLogisticsPerson().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getLogisticsPerson().hashCode();
        return result;
    }
}
