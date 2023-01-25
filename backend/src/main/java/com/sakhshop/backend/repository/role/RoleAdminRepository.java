package com.sakhshop.backend.repository.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.role.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAdminRepository extends JpaRepository<RoleAdmin, Long> {

    RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum);

}