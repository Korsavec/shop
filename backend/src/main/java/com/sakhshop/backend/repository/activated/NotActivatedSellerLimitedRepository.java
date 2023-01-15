package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedSellerLimited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedSellerLimitedRepository extends JpaRepository<NotActivatedSellerLimited, Long> {
}