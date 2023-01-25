package com.sakhshop.backend.repository.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.role.RoleLogisticsCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleLogisticsCompanyRepository extends JpaRepository<RoleLogisticsCompany, Long> {

    RoleLogisticsCompany findRoleLogisticsCompanyByRoleEnum(RoleEnum roleEnum);

}
