package com.sakhshop.backend.service.jpa;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedLogisticsCompany;
import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.activation.NotActivatedUser;
import com.sakhshop.backend.models.admin.Admin;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.models.role.RoleAdmin;
import com.sakhshop.backend.models.role.RoleLogisticsCompany;
import com.sakhshop.backend.models.role.RoleSeller;
import com.sakhshop.backend.models.role.RoleUser;
import com.sakhshop.backend.models.seller.Seller;
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

    @Transactional
    void deleteAllUser(Iterable<NotActivatedUser> entityList);

    Iterable<NotActivatedUser> findAllDateDeletionUser();

    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleUser findRoleUserByRoleEnum(RoleEnum roleEnum);

    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteListUser(Iterable<User> entityList);

    Boolean existsUserByEmail(String email);

    List<User> findAllUserById(Iterable<Long> entityList);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByToken (String token);

    @Transactional
    void saveUser(User user);

    @Transactional
    void updateUserPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateUserTokenByEmail(String token, String email);





    // >>>>>>>>>>>>>>>>>>>> Seller <<<<<<<<<<<<<<<<<<<<





    // NotActivatedSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteAllSeller(Iterable<NotActivatedSeller> entityList);
    Iterable<NotActivatedSeller> findAllDateDeletionSeller();

    // RoleSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleSeller findRoleSellerByRoleEnum(RoleEnum roleEnum);

    // Seller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteListSeller(Iterable<Seller> entityList);

    Boolean existsSellerByBankAccount(String bankAccount);

    Boolean existsSellerByBeakBank(int beakBank);

    Boolean existsSellerByEmail(String email);

    Boolean existsSellerByImgPassport(String imgPassport);

    Boolean existsSellerByInn(Long inn);

    Boolean existsSellerByNumberPassport(Long numberPassport);

    Boolean existsSellerByPhone(Long phone);

    Boolean existsSellerByShopName(String shopName);

    List<Seller> findAllSellerById(Iterable<Long> entityList);

    Optional<Seller> findSellerByEmail(String email);

    Optional<Seller> findSellerByToken (String token);

    @Transactional
    void saveSeller(Seller seller);

    @Transactional
    void updateSellerPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateSellerTokenByEmail(String token, String email);





    // >>>>>>>>>>>>>>>>>>>> Admin <<<<<<<<<<<<<<<<<<<<





    // RoleAdmin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum);

    // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    Boolean existsAdminByEmail(String email);

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByToken (String token);

    @Transactional
    void updateAdminPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateAdminTokenByEmail(String token, String email);





    // >>>>>>>>>>>>>>>>>>>> LogisticsCompany <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteAllLogisticsCompany(Iterable<NotActivatedLogisticsCompany> entityList);
    Iterable<NotActivatedLogisticsCompany> findAllDateDeletionLogisticsCompany();

    // RoleLogisticsCompany >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleLogisticsCompany findRoleLogisticsCompanyByRoleEnum(RoleEnum roleEnum);

    // LogisticsCompany >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteListLogisticsCompany(Iterable<LogisticsCompany> entityList);

    Boolean existsLogisticsCompanyByBankAccount(String bankAccount);

    Boolean existsLogisticsCompanyByBeakBank(int beakBank);

    Boolean existsLogisticsCompanyByEmail(String email);

    Boolean existsLogisticsCompanyByImgPassport(String imgPassport);

    Boolean existsLogisticsCompanyByInn(Long inn);

    Boolean existsLogisticsCompanyByNumberPassport(Long numberPassport);

    Boolean existsLogisticsCompanyByPhone(Long phone);

    List<LogisticsCompany> findAllLogisticsCompanyById(Iterable<Long> entityList);

    Optional<LogisticsCompany> findLogisticsCompanyByEmail(String email);

    Optional<LogisticsCompany> findLogisticsCompanyByToken (String token);

    @Transactional
    void saveLogisticsCompany(LogisticsCompany logisticsCompany);

    @Transactional
    void updateLogisticsCompanyPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateLogisticsCompanyTokenByEmail(String token, String email);

}
