package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedSeller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedSellerRepository extends JpaRepository<NotActivatedSeller, Long> {
}