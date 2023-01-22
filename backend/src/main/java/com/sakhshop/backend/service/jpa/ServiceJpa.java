package com.sakhshop.backend.service.jpa;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.activation.NotActivatedUser;
import com.sakhshop.backend.models.admin.Admin;
import com.sakhshop.backend.models.role.RoleAdmin;
import com.sakhshop.backend.models.role.RoleSeller;
import com.sakhshop.backend.models.role.RoleUser;
import com.sakhshop.backend.models.seller.person.Seller;
import com.sakhshop.backend.models.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public interface ServiceJpa {


    // >>>>>>>>>>>>>>>>>>>> User <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>
    Iterable<NotActivatedUser> findAllDateDeletionUser();
    @Transactional
    void deleteAllUser(Iterable<NotActivatedUser> entityList);




    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>
    RoleUser findByRoleEnumUser(RoleEnum roleEnum);



    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>
    List<User> findAllByIdUser(Iterable<Long> entityList);
    @Transactional
    void deleteListUser(Iterable<User> entityList);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByToken (String token);
    Boolean existsByEmailUser(String email);

    @Transactional
    void updateTokenByEmailUser(String token, String email);

    @Transactional
    void updatePasswordTokenByEmailUser(String token, String password, String email);
    @Transactional
    void saveUser(User user);








    // >>>>>>>>>>>>>>>>>>>> Seller <<<<<<<<<<<<<<<<<<<<





    // NotActivatedSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Transactional
    void deleteAllSeller(Iterable<NotActivatedSeller> entityList);
    Iterable<NotActivatedSeller> findAllDateDeletionSeller();


    // RoleSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>
    RoleSeller findByRoleEnumSeller(RoleEnum roleEnum);



    List<Seller> findAllByIdSeller(Iterable<Long> entityList);
    @Transactional
    void deleteListSeller(Iterable<Seller> entityList);




    Optional<Seller> findSellerByEmail(String email);
    Boolean existsByPhone(Long phone);
    Optional<Seller> findSellerByToken (String token);
    Boolean existsByEmailSeller(String email);
    @Transactional
    void updateTokenByEmailSeller(String token, String email);


    Boolean existsByNumberPassportSeller(Long numberPassport);
    Boolean existsByInnSeller(Long inn);
    Boolean existsByShopNameSeller(String shopName);
    Boolean existsByImgPassport(String imgPassport);
    Boolean existsByBankAccount(String bankAccount);
    Boolean existsByBeakBank(int beakBank);

    @Transactional
    void updatePasswordTokenByEmailSeller(String token, String password, String email);

    @Transactional
    void saveSeller(Seller seller);







    // >>>>>>>>>>>>>>>>>>>> Admin <<<<<<<<<<<<<<<<<<<<










    // RoleAdmin >>>>>>>>>>>>>>>>>>>>>>>>>>>
    RoleAdmin findByRoleEnumAdmin(RoleEnum roleEnum);



    // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>


    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByToken (String token);
    Boolean existsByEmailAdmin(String email);

    @Transactional
    void updateTokenByEmailAdmin(String token, String email);

    @Transactional
    void updatePasswordTokenByEmailAdmin(String token, String password, String email);








}
