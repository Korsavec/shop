package com.sakhshop.backend.service.jpa;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.activation.NotActivatedUser;
import com.sakhshop.backend.models.role.RoleSeller;
import com.sakhshop.backend.models.role.RoleUser;
import com.sakhshop.backend.models.seller.person.Seller;
import com.sakhshop.backend.models.user.User;
import com.sakhshop.backend.repository.SellerRepository;
import com.sakhshop.backend.repository.UserRepository;
import com.sakhshop.backend.repository.activated.NotActivatedSellerRepository;
import com.sakhshop.backend.repository.activated.NotActivatedUserRepository;
import com.sakhshop.backend.repository.role.RoleSellerRepository;
import com.sakhshop.backend.repository.role.RoleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceJpaImpl implements ServiceJpa {


    private final
    UserRepository userRepository;

    private final
    RoleUserRepository roleUserRepository;

    private final
    SellerRepository sellerRepository;

    private final
    NotActivatedUserRepository notActivatedUserRepository;

    private final
    RoleSellerRepository roleSellerRepository;

    private final
    NotActivatedSellerRepository notActivatedSellerRepository;

    public ServiceJpaImpl(UserRepository userRepository, RoleUserRepository roleUserRepository, SellerRepository sellerRepository, NotActivatedUserRepository notActivatedUserRepository, RoleSellerRepository roleSellerRepository, NotActivatedSellerRepository notActivatedSellerRepository ) {
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.sellerRepository = sellerRepository;
        this.notActivatedUserRepository = notActivatedUserRepository;
        this.roleSellerRepository = roleSellerRepository;
        this.notActivatedSellerRepository = notActivatedSellerRepository;
    }


    // >>>>>>>>>>>>>>>>>>>> User <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public Iterable<NotActivatedUser> findAllDateDeletionUser() {
        return notActivatedUserRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteAllUser(Iterable<NotActivatedUser> entityList) {
        notActivatedUserRepository.deleteAll(entityList);
    }


    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public RoleUser findByRoleEnumUser(RoleEnum roleEnum) {
        return roleUserRepository.findByRoleEnum(roleEnum);
    }


    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public List<User> findAllByIdUser(Iterable<Long> entityList) {
        return userRepository.findAllById(entityList);
    }
    @Override
    @Transactional
    public void deleteListUser(Iterable<User> entityList) {
        userRepository.deleteAll(entityList);
    }
    @Override
    public Optional<User> findUserByEmailUser(String email) {
        return userRepository.findUserByEmail(email);
    }
    @Override
    public Optional<User> findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }
    @Override
    public Boolean existsByEmailUser(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public void updateTokenByEmailUser(String token, String email) {
        userRepository.updateTokenByEmail(token, email);
    }
    @Transactional
    @Override
    public void updatePasswordTokenByEmailUser(String token, String password, String email) {
        userRepository.updatePasswordTokenByEmail(token, password, email);
    }
    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
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
    public RoleSeller findByRoleEnumSeller(RoleEnum roleEnum) {
        return roleSellerRepository.findByRoleEnum(roleEnum);
    }


    // Seller >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public List<Seller> findAllByIdSeller(Iterable<Long> entityList) {
        return sellerRepository.findAllById(entityList);
    }
    @Override
    @Transactional
    public void deleteListSeller(Iterable<Seller> entityList) {
        sellerRepository.deleteAll(entityList);
    }

    @Override
    public Optional<Seller> findSellerByEmail(String email) {
        return sellerRepository.findSellerByEmail(email);
    }

    @Override
    public Boolean existsByPhone(Long phone) {
        return sellerRepository.existsByPhone(phone);
    }

    @Override
    public Optional<Seller> findSellerByToken(String token) {
        return sellerRepository.findSellerByToken(token);
    }

    @Override
    public Boolean existsByEmailSeller(String email) {
        return sellerRepository.existsByEmail(email);
    }

    @Override
    public void updateTokenByEmailSeller(String token, String email) {
        sellerRepository.updateTokenByEmail(token, email);
    }

    @Override
    public Boolean existsByNumberPassportSeller(Long numberPassport) {
        return sellerRepository.existsByNumberPassport(numberPassport);
    }
    @Override
    public Boolean existsByInnSeller(Long inn) {
        return sellerRepository.existsByInn(inn);
    }
    @Override
    public Boolean existsByShopNameSeller(String shopName) {
        return sellerRepository.existsByShopName(shopName);
    }

    @Override
    public Boolean existsByImgPassport(String imgPassport) {
        return sellerRepository.existsByImgPassport(imgPassport);
    }

    @Override
    public Boolean existsByBankAccount(String bankAccount) {
        return sellerRepository.existsByBankAccount(bankAccount);
    }

    @Override
    public Boolean existsByBeakBank(int beakBank) {
        return sellerRepository.existsByBeakBank(beakBank);
    }


    @Override
    public void updatePasswordTokenByEmailSeller(String token, String password, String email) {
        sellerRepository.updatePasswordTokenByEmail(token, password, email);
    }

    @Override
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }


}
