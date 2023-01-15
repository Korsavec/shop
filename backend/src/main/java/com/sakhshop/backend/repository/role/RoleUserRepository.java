package com.sakhshop.backend.repository.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.role.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

  RoleUser findByRoleEnum(RoleEnum roleEnum);
}
