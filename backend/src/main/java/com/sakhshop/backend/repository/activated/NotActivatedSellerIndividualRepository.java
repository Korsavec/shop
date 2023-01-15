package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedSellerIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedSellerIndividualRepository extends JpaRepository<NotActivatedSellerIndividual, Long> {
}