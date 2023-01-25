package com.sakhshop.backend.repository;

import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface LogisticsCompanyRepository extends JpaRepository<LogisticsCompany, Long> {

    Boolean existsLogisticsCompanyByBankAccount(String bankAccount);

    Boolean existsLogisticsCompanyByBeakBank(int beakBank);

    Boolean existsLogisticsCompanyByEmail(String email);

    Boolean existsLogisticsCompanyByImgPassport(String imgPassport);

    Boolean existsLogisticsCompanyByInn(Long inn);

    Boolean existsLogisticsCompanyByNumberPassport(Long numberPassport);

    Boolean existsLogisticsCompanyByPhone(Long phone);

    Optional<LogisticsCompany> findLogisticsCompanyByEmail(String email);

    Optional<LogisticsCompany> findLogisticsCompanyByToken (String token);

    @Transactional
    @Modifying
    @Query("UPDATE LogisticsCompany l SET l.token = :token, l.password = :password where l.email = :email")
    void updateLogisticsCompanyPasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE LogisticsCompany l SET l.token = :token where l.email = :email")
    void updateLogisticsCompanyTokenByEmail(@Param("token") String token, @Param("email") String email);


}
