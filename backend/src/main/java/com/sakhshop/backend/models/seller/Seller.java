package com.sakhshop.backend.models.seller;

import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.product.Product;
import com.sakhshop.backend.models.role.RoleSeller;
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
@Table(name = "model_seller")
public class Seller implements Serializable {

    @Serial
    private static final long serialVersionUID = -1878097864951107138L;

    // Это ID пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_seller")
    @SequenceGenerator(name = "seq_a_seller", allocationSize = 1)
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








    // Это денежный баланс продавца
    @Column(name="balance", columnDefinition = "Decimal(12,2) default '0.00'", length = 10)
    private double balance;

    // Это номер паспорта продавца
    @NaturalId
    @Column(name = "number_passport", nullable = false, unique = true, length = 10)
    private Long numberPassport;

    // Это ИНН персоны продавца
    @NaturalId
    @Column(name = "inn", nullable = false, unique = true, length = 12)
    private Long inn;

    // Это название магазина ИП продавца
    @NaturalId
    @Column(name = "shop_name", nullable = false, unique = true, length = 30)
    private String shopName;

    // Это ссылка на изображение паспорта. Полный путь.
    @Column(name = "img_passport", nullable = false, unique = true, length = 150)
    private String imgPassport;




    // Это регион персоны продавца
    @Column(name = "region", nullable = false, length = 7)
    private String region;

    // Это город персоны продавца
    @Column(name = "city", nullable = false, length = 25)
    private String city;

    // Это улица персоны продавца
    @Column(name = "street", nullable = false, length = 50)
    private String street;

    // Это дом персоны продавца. Пример 12/4
    @Column(name = "house", nullable = false, length = 11)
    private String house;

    // Это корпус персоны продавца. Пример 9999a/9999a
    @Column(name = "building", length = 11)
    private String building;

    // Это квартира/офис персоны продавца
    @Column(name="apartment", columnDefinition = "smallint", length = 4)
    private int apartment;




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




    // Это дата создания учётной записи пользователя
    @Column(name = "date_created_seller", nullable = false)
    private Instant dateCreatedSeller;

    // Это ip адрес с которого была зарегистрирована учётная запись пользователя
    @Column(name = "ip_address_registration", nullable = false, length = 39)
    private String ipAddressRegistration;

    // Это ip адрес с которого был подтверждён адрес электронной почты пользователя
    @Column(name = "ip_address_reg_confirm", length = 39)
    private String ipAddressRegConfirm;

    // Это ip адрес с которого был осуществлён первый вход в учётную запись пользователя
    @Column(name = "ip_address_first_entrance", length = 39)
    private String ipAddressFirstEntrance;

    @Column(name = "ip_address_last_entrance", length = 39)
    private String ipAddressLastEntrance;








    // Это таблица связей ManyToMany PrivatePerson и RoleUser
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_a_seller_and_role_seller",
            joinColumns = @JoinColumn(name = "seller_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_seller_id", referencedColumnName = "id"))
    private Set<RoleSeller> sellerRoles = new LinkedHashSet<>();


    // Ссылка на сущность товара
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sellers", cascade = CascadeType.PERSIST)
    private Set<Product> products = new LinkedHashSet<>();


    // Сущность активации аккаунта
    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotActivatedSeller notActivatedSeller;


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

    public Long getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(Long numberPassport) {
        this.numberPassport = numberPassport;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getImgPassport() {
        return imgPassport;
    }

    public void setImgPassport(String imgPassport) {
        this.imgPassport = imgPassport;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
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

    public Instant getDateCreatedSeller() {
        return dateCreatedSeller;
    }

    public void setDateCreatedSeller(Instant dateCreatedSeller) {
        this.dateCreatedSeller = dateCreatedSeller;
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

    public Set<RoleSeller> getSellerRoles() {
        return sellerRoles;
    }

    public void setSellerRoles(Set<RoleSeller> sellerRoles) {
        this.sellerRoles = sellerRoles;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public NotActivatedSeller getNotActivatedSeller() {
        return notActivatedSeller;
    }

    public void setNotActivatedSeller(NotActivatedSeller notActivatedSeller) {
        this.notActivatedSeller = notActivatedSeller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;

        if (Double.compare(seller.getBalance(), getBalance()) != 0) return false;
        if (getApartment() != seller.getApartment()) return false;
        if (getBeakBank() != seller.getBeakBank()) return false;
        if (getKppBank() != seller.getKppBank()) return false;
        if (isEnabled() != seller.isEnabled()) return false;
        if (isApproval() != seller.isApproval()) return false;
        if (isAccountNonLocked() != seller.isAccountNonLocked()) return false;
        if (!getId().equals(seller.getId())) return false;
        if (!getPhone().equals(seller.getPhone())) return false;
        if (!getEmail().equals(seller.getEmail())) return false;
        if (!getPassword().equals(seller.getPassword())) return false;
        if (!getUsername().equals(seller.getUsername())) return false;
        if (!getSurname().equals(seller.getSurname())) return false;
        if (!getName().equals(seller.getName())) return false;
        if (!getMiddleName().equals(seller.getMiddleName())) return false;
        if (!getDateBirth().equals(seller.getDateBirth())) return false;
        if (!getNumberPassport().equals(seller.getNumberPassport())) return false;
        if (!getInn().equals(seller.getInn())) return false;
        if (!getShopName().equals(seller.getShopName())) return false;
        if (!getImgPassport().equals(seller.getImgPassport())) return false;
        if (!getRegion().equals(seller.getRegion())) return false;
        if (!getCity().equals(seller.getCity())) return false;
        if (!getStreet().equals(seller.getStreet())) return false;
        if (!getHouse().equals(seller.getHouse())) return false;
        if (!getBuilding().equals(seller.getBuilding())) return false;
        if (!getBankAccount().equals(seller.getBankAccount())) return false;
        if (!getBankName().equals(seller.getBankName())) return false;
        if (!getCorrespondentAccount().equals(seller.getCorrespondentAccount())) return false;
        if (!getInnBank().equals(seller.getInnBank())) return false;
        if (!getToken().equals(seller.getToken())) return false;
        if (!getDateCreatedSeller().equals(seller.getDateCreatedSeller())) return false;
        if (!getIpAddressRegistration().equals(seller.getIpAddressRegistration())) return false;
        if (!getIpAddressRegConfirm().equals(seller.getIpAddressRegConfirm())) return false;
        if (!getIpAddressFirstEntrance().equals(seller.getIpAddressFirstEntrance())) return false;
        if (!getIpAddressLastEntrance().equals(seller.getIpAddressLastEntrance())) return false;
        if (!getSellerRoles().equals(seller.getSellerRoles())) return false;
        if (!getProducts().equals(seller.getProducts())) return false;
        return getNotActivatedSeller().equals(seller.getNotActivatedSeller());
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
        result = 31 * result + getNumberPassport().hashCode();
        result = 31 * result + getInn().hashCode();
        result = 31 * result + getShopName().hashCode();
        result = 31 * result + getImgPassport().hashCode();
        result = 31 * result + getRegion().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getHouse().hashCode();
        result = 31 * result + getBuilding().hashCode();
        result = 31 * result + getApartment();
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
        result = 31 * result + getDateCreatedSeller().hashCode();
        result = 31 * result + getIpAddressRegistration().hashCode();
        result = 31 * result + getIpAddressRegConfirm().hashCode();
        result = 31 * result + getIpAddressFirstEntrance().hashCode();
        result = 31 * result + getIpAddressLastEntrance().hashCode();
        result = 31 * result + getSellerRoles().hashCode();
        result = 31 * result + getProducts().hashCode();
        result = 31 * result + getNotActivatedSeller().hashCode();
        return result;
    }
}
