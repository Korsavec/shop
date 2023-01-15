package com.sakhshop.backend.repository;

import com.sakhshop.backend.models.seller.llc.SellerLimited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerLimitedRepository extends JpaRepository<SellerLimited, Long> {
}