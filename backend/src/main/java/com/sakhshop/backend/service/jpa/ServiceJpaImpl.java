package com.sakhshop.backend.service.jpa;

import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedSellerIndividual;
import com.sakhshop.backend.models.activation.NotActivatedSellerLimited;
import com.sakhshop.backend.models.activation.NotActivatedSellerPerson;
import com.sakhshop.backend.models.activation.NotActivatedUser;
import com.sakhshop.backend.models.role.RoleSellerPerson;
import com.sakhshop.backend.models.role.RoleUser;
import com.sakhshop.backend.models.seller.ie.SellerIndividual;
import com.sakhshop.backend.models.seller.llc.SellerLimited;
import com.sakhshop.backend.models.seller.person.SellerPerson;
import com.sakhshop.backend.models.user.User;
import com.sakhshop.backend.repository.SellerIndividualRepository;
import com.sakhshop.backend.repository.SellerLimitedRepository;
import com.sakhshop.backend.repository.SellerPersonRepository;
import com.sakhshop.backend.repository.UserRepository;
import com.sakhshop.backend.repository.activated.NotActivatedSellerIndividualRepository;
import com.sakhshop.backend.repository.activated.NotActivatedSellerLimitedRepository;
import com.sakhshop.backend.repository.activated.NotActivatedSellerPersonRepository;
import com.sakhshop.backend.repository.activated.NotActivatedUserRepository;
import com.sakhshop.backend.repository.role.RoleSellerPersonRepository;
import com.sakhshop.backend.repository.role.RoleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceJpaImpl implements Serializable, ServiceJpa {


    @Serial
    private static final long serialVersionUID = 1983548638084137328L;

    private final
    UserRepository userRepository;

    private final
    RoleUserRepository roleUserRepository;

    private final
    SellerPersonRepository sellerPersonRepository;

    private final
    SellerLimitedRepository sellerLimitedRepository;

    private final
    SellerIndividualRepository sellerIndividualRepository;

    private final
    NotActivatedUserRepository notActivatedUserRepository;

    private final
    RoleSellerPersonRepository roleSellerPersonRepository;

    private final
    NotActivatedSellerPersonRepository notActivatedSellerPersonRepository;

    private final
    NotActivatedSellerLimitedRepository notActivatedSellerLimitedRepository;

    private final
    NotActivatedSellerIndividualRepository notActivatedSellerIndividualRepository;

    public ServiceJpaImpl(UserRepository userRepository, RoleUserRepository roleUserRepository, SellerPersonRepository sellerPersonRepository, SellerLimitedRepository sellerLimitedRepository, SellerIndividualRepository sellerIndividualRepository, NotActivatedUserRepository notActivatedUserRepository, RoleSellerPersonRepository roleSellerPersonRepository, NotActivatedSellerPersonRepository notActivatedSellerPersonRepository, NotActivatedSellerLimitedRepository notActivatedSellerLimitedRepository, NotActivatedSellerIndividualRepository notActivatedSellerIndividualRepository) {
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.sellerPersonRepository = sellerPersonRepository;
        this.sellerLimitedRepository = sellerLimitedRepository;
        this.sellerIndividualRepository = sellerIndividualRepository;
        this.notActivatedUserRepository = notActivatedUserRepository;
        this.roleSellerPersonRepository = roleSellerPersonRepository;
        this.notActivatedSellerPersonRepository = notActivatedSellerPersonRepository;
        this.notActivatedSellerLimitedRepository = notActivatedSellerLimitedRepository;
        this.notActivatedSellerIndividualRepository = notActivatedSellerIndividualRepository;
    }


    // >>>>>>>>>>>>>>>>>>>> SellerIndividual <<<<<<<<<<<<<<<<<<<<






    // NotActivatedSellerIndividual >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public Iterable<NotActivatedSellerIndividual> findAllDateDeletionSellerIndividual() {
        return notActivatedSellerIndividualRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteAllSellerIndividual(Iterable<NotActivatedSellerIndividual> entityList) {
        notActivatedSellerIndividualRepository.deleteAll(entityList);
    }


    // SellerIndividual >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public List<SellerIndividual> findAllByIdSellerIndividual(Iterable<Long> entityList) {
        return sellerIndividualRepository.findAllById(entityList);
    }
    @Override
    @Transactional
    public void deleteListSellerIndividual(Iterable<SellerIndividual> entityList) {
        sellerIndividualRepository.deleteAll(entityList);
    }






    // >>>>>>>>>>>>>>>>>>>> SellerLimited <<<<<<<<<<<<<<<<<<<<






    // NotActivatedSellerLimited >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public Iterable<NotActivatedSellerLimited> findAllDateDeletionSellerLimited() {
        return notActivatedSellerLimitedRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteAllSellerLimited(Iterable<NotActivatedSellerLimited> entityList) {
        notActivatedSellerLimitedRepository.deleteAll(entityList);
    }



    // SellerLimited >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public List<SellerLimited> findAllByIdSellerLimited(Iterable<Long> entityList) {
        return sellerLimitedRepository.findAllById(entityList);
    }
    @Override
    @Transactional
    public void deleteListSellerLimited(Iterable<SellerLimited> entityList) {
        sellerLimitedRepository.deleteAll(entityList);
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








    // >>>>>>>>>>>>>>>>>>>> SellerPerson <<<<<<<<<<<<<<<<<<<<





    // NotActivatedSellerPerson >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    @Transactional
    public void deleteAllSellerPerson(Iterable<NotActivatedSellerPerson> entityList) {
        notActivatedSellerPersonRepository.deleteAll(entityList);
    }
    @Override
    public Iterable<NotActivatedSellerPerson> findAllDateDeletionSellerPerson() {
        return notActivatedSellerPersonRepository.findAll();
    }




    // RoleSellerPerson >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleSellerPerson findByRoleEnumSellerPerson(RoleEnum roleEnum) {
        return roleSellerPersonRepository.findByRoleEnum(roleEnum);
    }


    // SellerPerson >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public List<SellerPerson> findAllByIdSellerPerson(Iterable<Long> entityList) {
        return sellerPersonRepository.findAllById(entityList);
    }
    @Override
    @Transactional
    public void deleteListSellerPerson(Iterable<SellerPerson> entityList) {
        sellerPersonRepository.deleteAll(entityList);
    }

    @Override
    public Optional<SellerPerson> findSellerPersonByEmail(String email) {
        return sellerPersonRepository.findSellerPersonByEmail(email);
    }

    @Override
    public Boolean existsByPhone(Long phone) {
        return sellerPersonRepository.existsByPhone(phone);
    }

    @Override
    public Optional<SellerPerson> findSellerPersonByToken(String token) {
        return sellerPersonRepository.findSellerPersonByToken(token);
    }

    @Override
    public Boolean existsByEmailSellerPerson(String email) {
        return sellerPersonRepository.existsByEmail(email);
    }

    @Override
    public void updateTokenByEmailSellerPerson(String token, String email) {
        sellerPersonRepository.updateTokenByEmail(token, email);
    }

    @Override
    public Boolean existsByNumberPassportSellerPerson(Long numberPassport) {
        return sellerPersonRepository.existsByNumberPassport(numberPassport);
    }
    @Override
    public Boolean existsByInnSellerPerson(Long inn) {
        return sellerPersonRepository.existsByInn(inn);
    }
    @Override
    public Boolean existsByShopNameSellerPerson(String shopName) {
        return sellerPersonRepository.existsByShopName(shopName);
    }

    @Override
    public Boolean existsByImgPassport(String imgPassport) {
        return sellerPersonRepository.existsByImgPassport(imgPassport);
    }

    @Override
    public Boolean existsByBankAccount(String bankAccount) {
        return sellerPersonRepository.existsByBankAccount(bankAccount);
    }

    @Override
    public Boolean existsByBeakBank(int beakBank) {
        return sellerPersonRepository.existsByBeakBank(beakBank);
    }


    @Override
    public void updatePasswordTokenByEmailSellerPerson(String token, String password, String email) {
        sellerPersonRepository.updatePasswordTokenByEmail(token, password, email);
    }

    @Override
    public void saveSellerPerson(SellerPerson sellerPerson) {
        sellerPersonRepository.save(sellerPerson);
    }


}
