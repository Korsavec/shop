package com.sakhshop.backend.models.logistics.person;

import com.sakhshop.backend.models.activation.NotActivatedLogisticsPerson;
import com.sakhshop.backend.models.role.RoleLogisticsPerson;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_logistics_person")
public class LogisticsPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = -2488184603403093038L;

    // Это ID логистики
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_logistics_person")
    @SequenceGenerator(name = "seq_a_logistics_person", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это номер телефона пользователя
    @NaturalId
    @Column(name = "phone", nullable = false, unique = true, length = 10)
    private Long phone;

    // Это email пользователя
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 58)
    private String email;

    // Это пароль пользователя
    // Пароль шифруется, поэтому такой длинный
    @Column(name = "password", nullable = false, length = 65)
    private String password;

    // Это псевдоним пользователя
    @Column(name = "username", unique = true, length = 25)
    private String username;

    // Это настоящая фамилия пользователя
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    // Это настоящее имя пользователя
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    // Это настоящее отчество пользователя
    @Column(name = "middle_name", nullable = false, length = 45)
    private String middleName;

    // Это дата рождения пользователя
    @Column(name = "date_birth", nullable = false, length = 10)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBirth;





    // Это номер паспорта пользователя
    @NaturalId
    @Column(name = "number_passport", nullable = false, unique = true, length = 10)
    private Long numberPassport;

    // Это ссылка на изображение паспорта пользователя. Полный путь.
    @Column(name = "img_passport", nullable = false, unique = true, length = 150)
    private String imgPassport;


    // Имеется или не имеется медицинская комиссия
    @Column(name = "medical_checkup", nullable = false, length = 1)
    private boolean medicalCheckup;

    // Это ссылка на изображение медицинского осмотра пользователя. Полный путь.
    @Column(name = "img_medical_checkup", nullable = false, unique = true, length = 150)
    private String imgMedicalCheckup;





    // Это регион пользователя
    @Column(name = "region", nullable = false, length = 7)
    private String region;

    // Это город пользователя
    @Column(name = "city", nullable = false, length = 25)
    private String city;

    // Это улица пользователя
    @Column(name = "street", nullable = false, length = 50)
    private String street;


    // Это корпус пользователя. Пример 9999a/9999a
    @Column(name = "building", length = 11)
    private String building;


    // Это дом пользователя. Пример 12/4
    @Column(name = "house", nullable = false, length = 11)
    private String house;


    // Это квартира/офис пользователя
    @Column(name="apartment", columnDefinition = "smallint", length = 4)
    private int apartment;





    // Это денежный баланс пользователя
    @Column(name="balance", columnDefinition = "Decimal(12,2) default '0.00'", length = 10)
    private double balance;

    // Это банковский счёт получателя. Тип строка.
    @Column(name = "bank_account", nullable = false,  unique = true, length = 20)
    private String bankAccount;

    // Это БИК банка
    @Column(name = "beak_bank", nullable = false, length = 9)
    private int beakBank;

    // Это название банка
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    // Это корреспондентский счёт банка
    @Column(name = "correspondent_account", nullable = false, length = 20)
    private String correspondentAccount;

    // Это ИНН банка
    @NaturalId
    @Column(name = "inn_bank", nullable = false, length = 10)
    private Long innBank;

    // Это КПП банка
    @NaturalId
    @Column(name = "kpp_bank", nullable = false, length = 9)
    private int kppBank;





    // Установите значение true, если пользователь включен
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;

    // Установите значение true, если анкета продавца была одобрена
    @Column(name = "approval", nullable = false, length = 1)
    private boolean approval;

    // Token для подтверждения email адреса и сброса пароля
    @Column(name = "token", unique = true, length = 45)
    private String token;

    // Установите значение true, если учетная запись не заблокирована
    @Column(name = "account_non_locked", nullable = false, length = 1)
    private boolean accountNonLocked;





    // Это дата создания учётной записи
    @Column(name = "date_created_logistics_person", nullable = false)
    private Instant dateCreatedLogisticsPerson;

    // Это ip адрес с которого была зарегистрирована учётная запись пользователя
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;

    // Это ip адрес с которого был подтверждён адрес электронной почты пользователя
    @Column(name = "ip_address_reg_confirm", length = 39)
    private String ipAddressRegConfirm;

    // Это ip адрес с которого был осуществлён первый вход в учётную запись пользователя
    @Column(name = "ip_address_first_entrance", length = 39)
    private String ipAddressFirstEntrance;

    // Это ip адрес с которого был осуществлён последний вход в учётную запись пользователя
    @Column(name = "ip_address_last_entrance", length = 39)
    private String ipAddressLastEntrance;




    // Это таблица связей ManyToMany LogisticsPerson и RoleLogisticsPerson
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_a_logistics_person_and_role_logistics_person",
            joinColumns = @JoinColumn(name = "logistics_person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_logistics_person_id", referencedColumnName = "id"))
    private Set<RoleLogisticsPerson> logisticsPersonRoles = new LinkedHashSet<>();

    // Сущность активации аккаунта
    @OneToOne(mappedBy = "logisticsPerson", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotActivatedLogisticsPerson notActivatedLogisticsPerson;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Long getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(Long numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getImgPassport() {
        return imgPassport;
    }

    public void setImgPassport(String imgPassport) {
        this.imgPassport = imgPassport;
    }

    public boolean isMedicalCheckup() {
        return medicalCheckup;
    }

    public void setMedicalCheckup(boolean medicalCheckup) {
        this.medicalCheckup = medicalCheckup;
    }

    public String getImgMedicalCheckup() {
        return imgMedicalCheckup;
    }

    public void setImgMedicalCheckup(String imgMedicalCheckup) {
        this.imgMedicalCheckup = imgMedicalCheckup;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getBeakBank() {
        return beakBank;
    }

    public void setBeakBank(int beakBank) {
        this.beakBank = beakBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public Long getInnBank() {
        return innBank;
    }

    public void setInnBank(Long innBank) {
        this.innBank = innBank;
    }

    public int getKppBank() {
        return kppBank;
    }

    public void setKppBank(int kppBank) {
        this.kppBank = kppBank;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Instant getDateCreatedLogisticsPerson() {
        return dateCreatedLogisticsPerson;
    }

    public void setDateCreatedLogisticsPerson(Instant dateCreatedLogisticsPerson) {
        this.dateCreatedLogisticsPerson = dateCreatedLogisticsPerson;
    }

    public String getIpAddressRegistration() {
        return ipAddressRegistration;
    }

    public void setIpAddressRegistration(String ipAddressRegistration) {
        this.ipAddressRegistration = ipAddressRegistration;
    }

    public String getIpAddressRegConfirm() {
        return ipAddressRegConfirm;
    }

    public void setIpAddressRegConfirm(String ipAddressRegConfirm) {
        this.ipAddressRegConfirm = ipAddressRegConfirm;
    }

    public String getIpAddressFirstEntrance() {
        return ipAddressFirstEntrance;
    }

    public void setIpAddressFirstEntrance(String ipAddressFirstEntrance) {
        this.ipAddressFirstEntrance = ipAddressFirstEntrance;
    }

    public String getIpAddressLastEntrance() {
        return ipAddressLastEntrance;
    }

    public void setIpAddressLastEntrance(String ipAddressLastEntrance) {
        this.ipAddressLastEntrance = ipAddressLastEntrance;
    }

    public Set<RoleLogisticsPerson> getLogisticsPersonRoles() {
        return logisticsPersonRoles;
    }

    public void setLogisticsPersonRoles(Set<RoleLogisticsPerson> logisticsPersonRoles) {
        this.logisticsPersonRoles = logisticsPersonRoles;
    }

    public NotActivatedLogisticsPerson getNotActivatedLogisticsPerson() {
        return notActivatedLogisticsPerson;
    }

    public void setNotActivatedLogisticsPerson(NotActivatedLogisticsPerson notActivatedLogisticsPerson) {
        this.notActivatedLogisticsPerson = notActivatedLogisticsPerson;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogisticsPerson that)) return false;

        if (isMedicalCheckup() != that.isMedicalCheckup()) return false;
        if (getApartment() != that.getApartment()) return false;
        if (Double.compare(that.getBalance(), getBalance()) != 0) return false;
        if (getBeakBank() != that.getBeakBank()) return false;
        if (getKppBank() != that.getKppBank()) return false;
        if (isEnabled() != that.isEnabled()) return false;
        if (isApproval() != that.isApproval()) return false;
        if (isAccountNonLocked() != that.isAccountNonLocked()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getPhone().equals(that.getPhone())) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        if (!getPassword().equals(that.getPassword())) return false;
        if (!getUsername().equals(that.getUsername())) return false;
        if (!getSurname().equals(that.getSurname())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getMiddleName().equals(that.getMiddleName())) return false;
        if (!getDateBirth().equals(that.getDateBirth())) return false;
        if (!getNumberPassport().equals(that.getNumberPassport())) return false;
        if (!getImgPassport().equals(that.getImgPassport())) return false;
        if (!getImgMedicalCheckup().equals(that.getImgMedicalCheckup())) return false;
        if (!getRegion().equals(that.getRegion())) return false;
        if (!getCity().equals(that.getCity())) return false;
        if (!getStreet().equals(that.getStreet())) return false;
        if (!getBuilding().equals(that.getBuilding())) return false;
        if (!getHouse().equals(that.getHouse())) return false;
        if (!getBankAccount().equals(that.getBankAccount())) return false;
        if (!getBankName().equals(that.getBankName())) return false;
        if (!getCorrespondentAccount().equals(that.getCorrespondentAccount())) return false;
        if (!getInnBank().equals(that.getInnBank())) return false;
        if (!getToken().equals(that.getToken())) return false;
        if (!getDateCreatedLogisticsPerson().equals(that.getDateCreatedLogisticsPerson())) return false;
        if (!getIpAddressRegistration().equals(that.getIpAddressRegistration())) return false;
        if (!getIpAddressRegConfirm().equals(that.getIpAddressRegConfirm())) return false;
        if (!getIpAddressFirstEntrance().equals(that.getIpAddressFirstEntrance())) return false;
        if (!getIpAddressLastEntrance().equals(that.getIpAddressLastEntrance())) return false;
        if (!getLogisticsPersonRoles().equals(that.getLogisticsPersonRoles())) return false;
        return getNotActivatedLogisticsPerson().equals(that.getNotActivatedLogisticsPerson());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getMiddleName().hashCode();
        result = 31 * result + getDateBirth().hashCode();
        result = 31 * result + getNumberPassport().hashCode();
        result = 31 * result + getImgPassport().hashCode();
        result = 31 * result + (isMedicalCheckup() ? 1 : 0);
        result = 31 * result + getImgMedicalCheckup().hashCode();
        result = 31 * result + getRegion().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getBuilding().hashCode();
        result = 31 * result + getHouse().hashCode();
        result = 31 * result + getApartment();
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getBankAccount().hashCode();
        result = 31 * result + getBeakBank();
        result = 31 * result + getBankName().hashCode();
        result = 31 * result + getCorrespondentAccount().hashCode();
        result = 31 * result + getInnBank().hashCode();
        result = 31 * result + getKppBank();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + (isApproval() ? 1 : 0);
        result = 31 * result + getToken().hashCode();
        result = 31 * result + (isAccountNonLocked() ? 1 : 0);
        result = 31 * result + getDateCreatedLogisticsPerson().hashCode();
        result = 31 * result + getIpAddressRegistration().hashCode();
        result = 31 * result + getIpAddressRegConfirm().hashCode();
        result = 31 * result + getIpAddressFirstEntrance().hashCode();
        result = 31 * result + getIpAddressLastEntrance().hashCode();
        result = 31 * result + getLogisticsPersonRoles().hashCode();
        result = 31 * result + getNotActivatedLogisticsPerson().hashCode();
        return result;
    }
}
