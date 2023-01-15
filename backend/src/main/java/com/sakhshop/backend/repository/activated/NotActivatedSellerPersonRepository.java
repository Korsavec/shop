package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedSellerPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedSellerPersonRepository extends JpaRepository<NotActivatedSellerPerson, Long> {
}