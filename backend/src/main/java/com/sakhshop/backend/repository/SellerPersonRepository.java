package com.sakhshop.backend.repository;

import com.sakhshop.backend.models.seller.person.SellerPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface SellerPersonRepository extends JpaRepository<SellerPerson, Long> {

    Optional<SellerPerson> findSellerPersonByEmail(String email);
    Boolean existsByPhone(Long phone);
    Optional<SellerPerson> findSellerPersonByToken (String token);
    Boolean existsByEmail(String email);
    Boolean existsByNumberPassport(Long numberPassport);
    Boolean existsByInn(Long inn);
    Boolean existsByShopName(String shopName);
    Boolean existsByImgPassport(String imgPassport);
    Boolean existsByBankAccount(String bankAccount);
    Boolean existsByBeakBank(int beakBank);


    @Transactional
    @Modifying
    @Query("UPDATE SellerPerson s SET s.token = :token where s.email = :email")
    void updateTokenByEmail(@Param("token") String token, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE SellerPerson s SET s.token = :token, s.password = :password where s.email = :email")
    void updatePasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);
}