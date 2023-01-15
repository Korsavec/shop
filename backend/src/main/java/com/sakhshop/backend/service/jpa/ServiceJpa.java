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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public interface ServiceJpa {


    // >>>>>>>>>>>>>>>>>>>> SellerIndividual <<<<<<<<<<<<<<<<<<<<




    // NotActivatedSellerIndividual >>>>>>>>>>>>>>>>>>>>>>>>>>>
    Iterable<NotActivatedSellerIndividual> findAllDateDeletionSellerIndividual();
    @Transactional
    void deleteAllSellerIndividual(Iterable<NotActivatedSellerIndividual> entityList);



    // SellerIndividual >>>>>>>>>>>>>>>>>>>>>>>>>>>
    List<SellerIndividual> findAllByIdSellerIndividual(Iterable<Long> entityList);
    @Transactional
    void deleteListSellerIndividual(Iterable<SellerIndividual> entityList);






    // >>>>>>>>>>>>>>>>>>>> SellerLimited <<<<<<<<<<<<<<<<<<<<






    // NotActivatedSellerLimited >>>>>>>>>>>>>>>>>>>>>>>>>>>
    Iterable<NotActivatedSellerLimited> findAllDateDeletionSellerLimited();
    @Transactional
    void deleteAllSellerLimited(Iterable<NotActivatedSellerLimited> entityList);



    // SellerLimited >>>>>>>>>>>>>>>>>>>>>>>>>>>
    List<SellerLimited> findAllByIdSellerLimited(Iterable<Long> entityList);
    @Transactional
    void deleteListSellerLimited(Iterable<SellerLimited> entityList);








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
    Optional<User> findUserByEmailUser(String email);
    Optional<User> findUserByToken (String token);
    Boolean existsByEmailUser(String email);

    @Transactional
    void updateTokenByEmailUser(String token, String email);

    @Transactional
    void updatePasswordTokenByEmailUser(String token, String password, String email);
    @Transactional
    void saveUser(User user);








    // >>>>>>>>>>>>>>>>>>>> SellerPerson <<<<<<<<<<<<<<<<<<<<





    // NotActivatedSellerPerson >>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Transactional
    void deleteAllSellerPerson(Iterable<NotActivatedSellerPerson> entityList);
    Iterable<NotActivatedSellerPerson> findAllDateDeletionSellerPerson();


    // RoleSellerPerson >>>>>>>>>>>>>>>>>>>>>>>>>>>
    RoleSellerPerson findByRoleEnumSellerPerson(RoleEnum roleEnum);



    List<SellerPerson> findAllByIdSellerPerson(Iterable<Long> entityList);
    @Transactional
    void deleteListSellerPerson(Iterable<SellerPerson> entityList);




    Optional<SellerPerson> findSellerPersonByEmail(String email);
    Boolean existsByPhone(Long phone);
    Optional<SellerPerson> findSellerPersonByToken (String token);
    Boolean existsByEmailSellerPerson(String email);
    @Transactional
    void updateTokenByEmailSellerPerson(String token, String email);


    Boolean existsByNumberPassportSellerPerson(Long numberPassport);
    Boolean existsByInnSellerPerson(Long inn);
    Boolean existsByShopNameSellerPerson(String shopName);
    Boolean existsByImgPassport(String imgPassport);
    Boolean existsByBankAccount(String bankAccount);
    Boolean existsByBeakBank(int beakBank);

    @Transactional
    void updatePasswordTokenByEmailSellerPerson(String token, String password, String email);

    @Transactional
    void saveSellerPerson(SellerPerson sellerPerson);

}
