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
import com.sakhshop.backend.repository.AdminRepository;
import com.sakhshop.backend.repository.LogisticsCompanyRepository;
import com.sakhshop.backend.repository.SellerRepository;
import com.sakhshop.backend.repository.UserRepository;
import com.sakhshop.backend.repository.activated.NotActivatedLogisticsCompanyRepository;
import com.sakhshop.backend.repository.activated.NotActivatedSellerRepository;
import com.sakhshop.backend.repository.activated.NotActivatedUserRepository;
import com.sakhshop.backend.repository.role.RoleAdminRepository;
import com.sakhshop.backend.repository.role.RoleLogisticsCompanyRepository;
import com.sakhshop.backend.repository.role.RoleSellerRepository;
import com.sakhshop.backend.repository.role.RoleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceJpaImpl implements ServiceJpa {


    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final LogisticsCompanyRepository logisticsCompanyRepository;


    private final RoleAdminRepository roleAdminRepository;
    private final RoleSellerRepository roleSellerRepository;
    private final RoleUserRepository roleUserRepository;
    private final RoleLogisticsCompanyRepository roleLogisticsCompanyRepository;



    private final NotActivatedSellerRepository notActivatedSellerRepository;
    private final NotActivatedUserRepository notActivatedUserRepository;
    private final NotActivatedLogisticsCompanyRepository notActivatedLogisticsCompanyRepository;

    public ServiceJpaImpl(AdminRepository adminRepository, SellerRepository sellerRepository, UserRepository userRepository, LogisticsCompanyRepository logisticsCompanyRepository, RoleAdminRepository roleAdminRepository, RoleSellerRepository roleSellerRepository, RoleUserRepository roleUserRepository, RoleLogisticsCompanyRepository roleLogisticsCompanyRepository, NotActivatedSellerRepository notActivatedSellerRepository, NotActivatedUserRepository notActivatedUserRepository, NotActivatedLogisticsCompanyRepository notActivatedLogisticsCompanyRepository) {
        this.adminRepository = adminRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.logisticsCompanyRepository = logisticsCompanyRepository;
        this.roleAdminRepository = roleAdminRepository;
        this.roleSellerRepository = roleSellerRepository;
        this.roleUserRepository = roleUserRepository;
        this.roleLogisticsCompanyRepository = roleLogisticsCompanyRepository;
        this.notActivatedSellerRepository = notActivatedSellerRepository;
        this.notActivatedUserRepository = notActivatedUserRepository;
        this.notActivatedLogisticsCompanyRepository = notActivatedLogisticsCompanyRepository;
    }



    // >>>>>>>>>>>>>>>>>>>> User <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteAllUser(Iterable<NotActivatedUser> entityList) {
        notActivatedUserRepository.deleteAll(entityList);
    }

    @Override
    public Iterable<NotActivatedUser> findAllDateDeletionUser() {
        return notActivatedUserRepository.findAll();
    }

    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleUser findRoleUserByRoleEnum(RoleEnum roleEnum) {
        return roleUserRepository.findRoleUserByRoleEnum(roleEnum);
    }

    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteListUser(Iterable<User> entityList) {
        userRepository.deleteAll(entityList);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public List<User> findAllUserById(Iterable<Long> entityList) {
        return userRepository.findAllById(entityList);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUserPasswordTokenByEmail(String token, String password, String email) {
        userRepository.updateUserPasswordTokenByEmail(token, password, email);
    }

    @Transactional
    @Override
    public void updateUserTokenByEmail(String token, String email) {
        userRepository.updateUserTokenByEmail(token, email);
    }





    // >>>>>>>>>>>>>>>>>>>> Seller <<<<<<<<<<<<<<<<<<<<





    // NotActivatedSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteAllSeller(Iterable<NotActivatedSeller> entityList) {
        notActivatedSellerRepository.deleteAll(entityList);
    }
    @Override
    public Iterable<NotActivatedSeller> findAllDateDeletionSeller() {
        return notActivatedSellerRepository.findAll();
    }

    // RoleSeller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleSeller findRoleSellerByRoleEnum(RoleEnum roleEnum) {
        return roleSellerRepository.findRoleSellerByRoleEnum(roleEnum);
    }

    // Seller >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteListSeller(Iterable<Seller> entityList) {
        sellerRepository.deleteAll(entityList);
    }

    @Override
    public Boolean existsSellerByBankAccount(String bankAccount) {
        return sellerRepository.existsSellerByBankAccount(bankAccount);
    }

    @Override
    public Boolean existsSellerByBeakBank(int beakBank) {
        return sellerRepository.existsSellerByBeakBank(beakBank);
    }

    @Override
    public Boolean existsSellerByEmail(String email) {
        return sellerRepository.existsSellerByEmail(email);
    }

    @Override
    public Boolean existsSellerByImgPassport(String imgPassport) {
        return sellerRepository.existsSellerByImgPassport(imgPassport);
    }

    @Override
    public Boolean existsSellerByInn(Long inn) {
        return sellerRepository.existsSellerByInn(inn);
    }

    @Override
    public Boolean existsSellerByNumberPassport(Long numberPassport) {
        return sellerRepository.existsSellerByNumberPassport(numberPassport);
    }

    @Override
    public Boolean existsSellerByPhone(Long phone) {
        return sellerRepository.existsSellerByPhone(phone);
    }

    @Override
    public Boolean existsSellerByShopName(String shopName) {
        return sellerRepository.existsSellerByShopName(shopName);
    }

    @Override
    public List<Seller> findAllSellerById(Iterable<Long> entityList) {
        return sellerRepository.findAllById(entityList);
    }

    @Override
    public Optional<Seller> findSellerByEmail(String email) {
        return sellerRepository.findSellerByEmail(email);
    }

    @Override
    public Optional<Seller> findSellerByToken(String token) {
        return sellerRepository.findSellerByToken(token);
    }

    @Override
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerPasswordTokenByEmail(String token, String password, String email) {
        sellerRepository.updateSellerPasswordTokenByEmail(token, password, email);
    }

    @Override
    public void updateSellerTokenByEmail(String token, String email) {
        sellerRepository.updateSellerTokenByEmail(token, email);
    }





    // >>>>>>>>>>>>>>>>>>>> Admin <<<<<<<<<<<<<<<<<<<<





    // RoleAdmin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum) {
        return roleAdminRepository.findRoleAdminByRoleEnum(roleEnum);
    }

    // // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public Boolean existsAdminByEmail(String email) {
        return adminRepository.existsAdminByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminByToken(String token) {
        return adminRepository.findAdminByToken(token);
    }

    @Override
    public void updateAdminPasswordTokenByEmail(String token, String password, String email) {
        adminRepository.updateAdminPasswordTokenByEmail(token, password, email); // ++++++++++++++++++
    }

    @Override
    public void updateAdminTokenByEmail(String token, String email) {
        adminRepository.updateAdminTokenByEmail(token, email);
    }





    // >>>>>>>>>>>>>>>>>>>> LogisticsCompany <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public void deleteAllLogisticsCompany(Iterable<NotActivatedLogisticsCompany> entityList) {
        notActivatedLogisticsCompanyRepository.deleteAll(entityList);
    }

    @Override
    public Iterable<NotActivatedLogisticsCompany> findAllDateDeletionLogisticsCompany() {
        return notActivatedLogisticsCompanyRepository.findAll();
    }

    // RoleLogisticsCompany >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleLogisticsCompany findRoleLogisticsCompanyByRoleEnum(RoleEnum roleEnum) {
        return roleLogisticsCompanyRepository.findRoleLogisticsCompanyByRoleEnum(roleEnum);
    }

    // LogisticsCompany >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public void deleteListLogisticsCompany(Iterable<LogisticsCompany> entityList) {
        logisticsCompanyRepository.deleteAll(entityList);
    }

    @Override
    public Boolean existsLogisticsCompanyByBankAccount(String bankAccount) {
        return logisticsCompanyRepository.existsLogisticsCompanyByBankAccount(bankAccount);
    }

    @Override
    public Boolean existsLogisticsCompanyByBeakBank(int beakBank) {
        return logisticsCompanyRepository.existsLogisticsCompanyByBeakBank(beakBank);
    }

    @Override
    public Boolean existsLogisticsCompanyByEmail(String email) {
        return logisticsCompanyRepository.existsLogisticsCompanyByEmail(email);
    }

    @Override
    public Boolean existsLogisticsCompanyByImgPassport(String imgPassport) {
        return logisticsCompanyRepository.existsLogisticsCompanyByImgPassport(imgPassport);
    }

    @Override
    public Boolean existsLogisticsCompanyByInn(Long inn) {
        return logisticsCompanyRepository.existsLogisticsCompanyByInn(inn);
    }

    @Override
    public Boolean existsLogisticsCompanyByNumberPassport(Long numberPassport) {
        return logisticsCompanyRepository.existsLogisticsCompanyByNumberPassport(numberPassport);
    }

    @Override
    public Boolean existsLogisticsCompanyByPhone(Long phone) {
        return logisticsCompanyRepository.existsLogisticsCompanyByPhone(phone);
    }

    @Override
    public List<LogisticsCompany> findAllLogisticsCompanyById(Iterable<Long> entityList) {
        return logisticsCompanyRepository.findAllById(entityList);
    }

    @Override
    public Optional<LogisticsCompany> findLogisticsCompanyByEmail(String email) {
        return logisticsCompanyRepository.findLogisticsCompanyByEmail(email);
    }

    @Override
    public Optional<LogisticsCompany> findLogisticsCompanyByToken(String token) {
        return logisticsCompanyRepository.findLogisticsCompanyByToken(token);
    }

    @Override
    public void saveLogisticsCompany(LogisticsCompany logisticsCompany) {
        logisticsCompanyRepository.save(logisticsCompany);
    }

    @Override
    public void updateLogisticsCompanyPasswordTokenByEmail(String token, String password, String email) {
        logisticsCompanyRepository.updateLogisticsCompanyPasswordTokenByEmail(token, password, email);
    }

    @Override
    public void updateLogisticsCompanyTokenByEmail(String token, String email) {
        logisticsCompanyRepository.updateLogisticsCompanyTokenByEmail(token, email);
    }

}
