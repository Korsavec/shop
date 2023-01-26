package com.sakhshop.backend.repository;

import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface LogisticsPersonRepository extends JpaRepository<LogisticsPerson, Long> {

    Boolean existsLogisticsPersonByBankAccount(String bankAccount);

    Boolean existsLogisticsPersonByBeakBank(int beakBank);

    Boolean existsLogisticsPersonByEmail(String email);


    Boolean existsLogisticsPersonByImgMedicalCheckup(String imgMedical);

    Boolean existsLogisticsPersonByImgPassport(String imgPassport);

    Boolean existsLogisticsPersonByNumberPassport(Long numberPassport);

    Boolean existsLogisticsPersonByPhone(Long phone);

    Optional<LogisticsPerson> findLogisticsPersonByEmail(String email);

    Optional<LogisticsPerson> findLogisticsPersonByToken (String token);

    @Transactional
    @Modifying
    @Query("UPDATE LogisticsPerson l SET l.token = :token, l.password = :password where l.email = :email")
    void updateLogisticsPersonPasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE LogisticsPerson l SET l.token = :token where l.email = :email")
    void updateLogisticsPersonTokenByEmail(@Param("token") String token, @Param("email") String email);

}
