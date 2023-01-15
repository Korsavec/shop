package com.sakhshop.backend.models.seller.ie;

import com.sakhshop.backend.models.activation.NotActivatedSellerIndividual;
import com.sakhshop.backend.models.product.Product;
import com.sakhshop.backend.models.role.RoleSellerIndividual;
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
@Table(name = "model_seller_individual")
public class SellerIndividual implements Serializable {

    @Serial
    private static final long serialVersionUID = 7895650151886271879L;

    // Это ID пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_seller_individual")
    @SequenceGenerator(name = "seq_a_seller_individual", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    // Это номер телефона пользователя
    @NaturalId
    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private Long phone;

    // Это email пользователя
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 58)
    private String email;

    // Это пароль пользователя
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

    // Это дата рождения ИП пользователя
    @Column(name = "date_birth", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBirth;






    // Это денежный баланс ИП продавца
    @Column(name="balance", columnDefinition="Decimal(12,2) default '0.00'", length = 10)
    private double balance;

    // Это номер паспорта ИП продавца
    @NaturalId
    @Column(name = "number_passport", nullable = false, unique = true, length = 10)
    private int numberPassport;

    // Это ИНН ИП продавца
    @NaturalId
    @Column(name = "inn", nullable = false, unique = true, length = 12)
    private int inn;

    // Это название магазина ИП продавца
    @Column(name = "name_shop", nullable = false, unique = true, length = 30)
    private String nameShop;

    // Это ссылка на изображение паспорта. Полный путь.
    @Column(name = "url_img_passport", length = 50)
    private String urlImgPassport;



    // Это ОГРНИП продавца
    @NaturalId
    @Column(name = "ogrnip", unique = true, length = 15)
    private int ogrnip;

    // Это ОКВЭД ИП продавца
    @Column(name = "okved", unique = true, length = 8)
    private String okved;





    // Это регион ИП продавца
    @Column(name = "region", nullable = false, length = 7)
    private String region;

    // Это город ИП продавца
    @Column(name = "city", nullable = false, length = 25)
    private String city;

    // Это улица ИП продавца
    @Column(name = "street", nullable = false, length = 50)
    private String street;

    // Это корпус ИП продавца
    @Column(name = "building", length = 4)
    private String building;

    // Это дом ИП продавца
    @Column(name = "house", nullable = false, length = 4)
    private String house;

    // Это квартира/офис ИП продавца
    @Column(name = "apartment", length = 4)
    private int apartment;





    // Это банковский счёт ИП продавца
    @Column(name = "bank_account", nullable = false, length = 20)
    private String bankAccount;

    // Это банковский счёт ИП продавца
    @Column(name = "beak_bank", nullable = false, length = 9)
    private String beakBank;

    // Это название банка ИП продавца
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    // Это БИК банка ИП продавца
    @Column(name = "correspondent_account", nullable = false, length = 20)
    private String correspondentAccount;



    // Установите значение true, если пользователь включен
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;

    // Token для подтверждения email адреса и сброса пароля
    @Column(name = "token", unique = true, length = 45)
    private String token;

    // Установите значение true, если учетная запись не заблокирована
    @Column(name = "account_non_locked", nullable = false, length = 1)
    private boolean accountNonLocked;

    // Это дата создания учётной записи пользователя
    @Column(name = "date_created_seller_individual", nullable = false)
    private Instant dateCreatedSellerIndividual;

    // Это ip адрес с которого была зарегистрирована учётная запись пользователя
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;

    // Это ip адрес с которого был подтверждён адрес электронной почты пользователя
    @Column(name = "ip_address_reg_confirm", length = 39)
    private String ipAddressRegConfirm;

    // Это ip адрес с которого был осуществлён первый вход в учётную запись пользователя
    @Column(name = "ip_address_first_entrance", length = 39)
    private String ipAddressFirstEntrance;

    // TODO необходимо реализовать, чтобы сохранять IP адрес последнего входа
    // Это ip адрес с которого был осуществлён последний вход в учётную запись пользователя
    @Column(name = "ip_address_last_entrance", length = 39)
    private String ipAddressLastEntrance;


    // Это таблица связей ManyToMany SellerIndividual и RoleUser
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_a_seller_individual_and_role_seller_individual",
            joinColumns = @JoinColumn(name = "seller_individual_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_seller_individuals_id", referencedColumnName = "id"))
    private Set<RoleSellerIndividual> roleSellerIndividuals = new LinkedHashSet<>();


    // Ссылка на сущность товара
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sellerIndividuals", cascade = CascadeType.PERSIST)
    private Set<Product> products = new LinkedHashSet<>();


    // Сущность активации аккаунта
    @OneToOne(mappedBy = "sellerIndividual", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotActivatedSellerIndividual notActivatedSellerIndividual;

    public SellerIndividual() {
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(int numberPassport) {
        this.numberPassport = numberPassport;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getUrlImgPassport() {
        return urlImgPassport;
    }

    public void setUrlImgPassport(String urlImgPassport) {
        this.urlImgPassport = urlImgPassport;
    }

    public int getOgrnip() {
        return ogrnip;
    }

    public void setOgrnip(int ogrnip) {
        this.ogrnip = ogrnip;
    }

    public String getOkved() {
        return okved;
    }

    public void setOkved(String okved) {
        this.okved = okved;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBeakBank() {
        return beakBank;
    }

    public void setBeakBank(String beakBank) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Instant getDateCreatedSellerIndividual() {
        return dateCreatedSellerIndividual;
    }

    public void setDateCreatedSellerIndividual(Instant dateCreatedSellerIndividual) {
        this.dateCreatedSellerIndividual = dateCreatedSellerIndividual;
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

    public Set<RoleSellerIndividual> getRoleSellerIndividuals() {
        return roleSellerIndividuals;
    }

    public void setRoleSellerIndividuals(Set<RoleSellerIndividual> roleSellerIndividuals) {
        this.roleSellerIndividuals = roleSellerIndividuals;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public NotActivatedSellerIndividual getNotActivatedSellerIndividual() {
        return notActivatedSellerIndividual;
    }

    public void setNotActivatedSellerIndividual(NotActivatedSellerIndividual notActivatedSellerIndividual) {
        this.notActivatedSellerIndividual = notActivatedSellerIndividual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellerIndividual that)) return false;

        if (Double.compare(that.getBalance(), getBalance()) != 0) return false;
        if (getNumberPassport() != that.getNumberPassport()) return false;
        if (getInn() != that.getInn()) return false;
        if (getOgrnip() != that.getOgrnip()) return false;
        if (getApartment() != that.getApartment()) return false;
        if (isEnabled() != that.isEnabled()) return false;
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
        if (!getNameShop().equals(that.getNameShop())) return false;
        if (!getUrlImgPassport().equals(that.getUrlImgPassport())) return false;
        if (!getOkved().equals(that.getOkved())) return false;
        if (!getRegion().equals(that.getRegion())) return false;
        if (!getCity().equals(that.getCity())) return false;
        if (!getStreet().equals(that.getStreet())) return false;
        if (!getBuilding().equals(that.getBuilding())) return false;
        if (!getHouse().equals(that.getHouse())) return false;
        if (!getBankAccount().equals(that.getBankAccount())) return false;
        if (!getBeakBank().equals(that.getBeakBank())) return false;
        if (!getBankName().equals(that.getBankName())) return false;
        if (!getCorrespondentAccount().equals(that.getCorrespondentAccount())) return false;
        if (!getToken().equals(that.getToken())) return false;
        if (!getDateCreatedSellerIndividual().equals(that.getDateCreatedSellerIndividual())) return false;
        if (!getIpAddressRegistration().equals(that.getIpAddressRegistration())) return false;
        if (!getIpAddressRegConfirm().equals(that.getIpAddressRegConfirm())) return false;
        if (!getIpAddressFirstEntrance().equals(that.getIpAddressFirstEntrance())) return false;
        if (!getIpAddressLastEntrance().equals(that.getIpAddressLastEntrance())) return false;
        if (!getRoleSellerIndividuals().equals(that.getRoleSellerIndividuals())) return false;
        if (!getProducts().equals(that.getProducts())) return false;
        return getNotActivatedSellerIndividual().equals(that.getNotActivatedSellerIndividual());
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
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getNumberPassport();
        result = 31 * result + getInn();
        result = 31 * result + getNameShop().hashCode();
        result = 31 * result + getUrlImgPassport().hashCode();
        result = 31 * result + getOgrnip();
        result = 31 * result + getOkved().hashCode();
        result = 31 * result + getRegion().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getBuilding().hashCode();
        result = 31 * result + getHouse().hashCode();
        result = 31 * result + getApartment();
        result = 31 * result + getBankAccount().hashCode();
        result = 31 * result + getBeakBank().hashCode();
        result = 31 * result + getBankName().hashCode();
        result = 31 * result + getCorrespondentAccount().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + getToken().hashCode();
        result = 31 * result + (isAccountNonLocked() ? 1 : 0);
        result = 31 * result + getDateCreatedSellerIndividual().hashCode();
        result = 31 * result + getIpAddressRegistration().hashCode();
        result = 31 * result + getIpAddressRegConfirm().hashCode();
        result = 31 * result + getIpAddressFirstEntrance().hashCode();
        result = 31 * result + getIpAddressLastEntrance().hashCode();
        result = 31 * result + getRoleSellerIndividuals().hashCode();
        result = 31 * result + getProducts().hashCode();
        result = 31 * result + getNotActivatedSellerIndividual().hashCode();
        return result;
    }
}
