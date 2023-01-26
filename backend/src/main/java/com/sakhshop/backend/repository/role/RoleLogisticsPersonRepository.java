package com.sakhshop.backend.repository.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.role.RoleLogisticsPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleLogisticsPersonRepository extends JpaRepository<RoleLogisticsPerson, Long> {

    RoleLogisticsPerson findRoleLogisticsPersonByRoleEnum(RoleEnum roleEnum);

}
