package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedLogisticsPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotActivatedLogisticsPersonRepository extends JpaRepository<NotActivatedLogisticsPerson, Long>  {
}
