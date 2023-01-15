package com.sakhshop.backend.repository.role;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.role.RoleSellerPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RoleSellerPersonRepository extends JpaRepository<RoleSellerPerson, Long> {

    RoleSellerPerson findByRoleEnum(RoleEnum roleEnum);
}
