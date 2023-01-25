package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedLogisticsCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedLogisticsCompanyRepository extends JpaRepository<NotActivatedLogisticsCompany, Long> {
}
