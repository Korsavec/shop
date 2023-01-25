package com.sakhshop.backend.repository;

import com.sakhshop.backend.models.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Boolean existsSellerByBankAccount(String bankAccount);

    Boolean existsSellerByBeakBank(int beakBank);

    Boolean existsSellerByEmail(String email);

    Boolean existsSellerByImgPassport(String imgPassport);

    Boolean existsSellerByInn(Long inn);

    Boolean existsSellerByNumberPassport(Long numberPassport);

    Boolean existsSellerByPhone(Long phone);

    Boolean existsSellerByShopName(String shopName);

    Optional<Seller> findSellerByEmail(String email);

    Optional<Seller> findSellerByToken (String token);

    @Transactional
    @Modifying
    @Query("UPDATE Seller s SET s.token = :token, s.password = :password where s.email = :email")
    void updateSellerPasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Seller s SET s.token = :token where s.email = :email")
    void updateSellerTokenByEmail(@Param("token") String token, @Param("email") String email);

}