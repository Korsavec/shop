package com.sakhshop.backend.repository.activated;

import com.sakhshop.backend.models.activation.NotActivatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface NotActivatedUserRepository extends JpaRepository<NotActivatedUser, Long> {


}