package com.sakhshop.backend.models.product;

import com.sakhshop.backend.models.seller.ie.SellerIndividual;
import com.sakhshop.backend.models.seller.llc.SellerLimited;
import com.sakhshop.backend.models.seller.person.SellerPerson;
import com.sakhshop.backend.models.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_product")
public class Product {

    // Это ID товара
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a_product")
    @SequenceGenerator(name = "seq_a_product", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;


    // Это название товара
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    // Это описание товара
    @Column(name = "description", length = 800)
    private String description;

    // Это бренд товара
    @Column(name = "brand", length = 50)
    private String brand;



    // Это ссылка на главное изображение товара. Полный путь.
    @Column(name = "url_img1", nullable = false, length = 50)
    private String urlImg1;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img2", length = 50)
    private String urlImg2;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img3", length = 50)
    private String urlImg3;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img4", length = 50)
    private String urlImg4;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img5", length = 50)
    private String urlImg5;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img6", length = 50)
    private String urlImg6;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img7", length = 50)
    private String urlImg7;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img8", length = 50)
    private String urlImg8;

    // Это ссылка на изображение товара. Полный путь.
    @Column(name = "url_img9", length = 50)
    private String urlImg9;



    // Это ключевое слово товара.
    @Column(name = "keyword1", length = 50)
    private String keyword1;

    // Это ключевое слово товара.
    @Column(name = "keyword2", length = 50)
    private String keyword2;

    // Это ключевое слово товара.
    @Column(name = "keyword3", length = 50)
    private String keyword3;

    // Это ключевое слово товара.
    @Column(name = "keyword4", length = 50)
    private String keyword4;

    // Это ключевое слово товара.
    @Column(name = "keyword5", length = 50)
    private String keyword5;



    // Это маркеры Bulle Point.
    @Column(name = "bullet1", length = 500)
    private String bullet1;

    // Это маркеры Bulle Point.
    @Column(name = "bullet2", length = 500)
    private String bullet2;

    // Это маркеры Bulle Point.
    @Column(name = "bullet3", length = 500)
    private String bullet3;

    // Это маркеры Bulle Point.
    @Column(name = "bullet4", length = 500)
    private String bullet4;

    // Это маркеры Bulle Point.
    @Column(name = "bullet5", length = 500)
    private String bullet5;



    // Это дата создания товара
    @Column(name = "date_created_product", nullable = false)
    private Instant dateCreatedProduct;

    // Это включён или выключен товар
    @Column(name = "enabled", nullable = false, length = 1)
    private boolean enabled;

    // Это ID создателя товара
    @Column(name = "product_creator_id", length = 25)
    private long productCreatorId;

    // Запрещает или разрешает модифицировать.
    @Column(name = "modified", nullable = false, length = 1)
    private boolean modified;




    // Это таблица связей ManyToMany Product и User
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_product_users",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    private Set<User> users = new LinkedHashSet<>();

    // Это таблица связей ManyToMany Product и SellerPerson
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_product_seller_persons",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seller_persons_id", referencedColumnName = "id"))
    private Set<SellerPerson> sellerPersons = new LinkedHashSet<>();

    // Это таблица связей ManyToMany Product и Individual
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_product_seller_individuals",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "individuals_id", referencedColumnName = "id"))
    private Set<SellerIndividual> sellerIndividuals = new LinkedHashSet<>();

    // Это таблица связей ManyToMany Product и SellerLimited
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "join_product_seller_limiteds",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seller_limiteds_id", referencedColumnName = "id"))
    private Set<SellerLimited> sellerLimiteds = new LinkedHashSet<>();





    public Product() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUrlImg1() {
        return urlImg1;
    }

    public void setUrlImg1(String urlImg1) {
        this.urlImg1 = urlImg1;
    }

    public String getUrlImg2() {
        return urlImg2;
    }

    public void setUrlImg2(String urlImg2) {
        this.urlImg2 = urlImg2;
    }

    public String getUrlImg3() {
        return urlImg3;
    }

    public void setUrlImg3(String urlImg3) {
        this.urlImg3 = urlImg3;
    }

    public String getUrlImg4() {
        return urlImg4;
    }

    public void setUrlImg4(String urlImg4) {
        this.urlImg4 = urlImg4;
    }

    public String getUrlImg5() {
        return urlImg5;
    }

    public void setUrlImg5(String urlImg5) {
        this.urlImg5 = urlImg5;
    }

    public String getUrlImg6() {
        return urlImg6;
    }

    public void setUrlImg6(String urlImg6) {
        this.urlImg6 = urlImg6;
    }

    public String getUrlImg7() {
        return urlImg7;
    }

    public void setUrlImg7(String urlImg7) {
        this.urlImg7 = urlImg7;
    }

    public String getUrlImg8() {
        return urlImg8;
    }

    public void setUrlImg8(String urlImg8) {
        this.urlImg8 = urlImg8;
    }

    public String getUrlImg9() {
        return urlImg9;
    }

    public void setUrlImg9(String urlImg9) {
        this.urlImg9 = urlImg9;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getBullet1() {
        return bullet1;
    }

    public void setBullet1(String bullet1) {
        this.bullet1 = bullet1;
    }

    public String getBullet2() {
        return bullet2;
    }

    public void setBullet2(String bullet2) {
        this.bullet2 = bullet2;
    }

    public String getBullet3() {
        return bullet3;
    }

    public void setBullet3(String bullet3) {
        this.bullet3 = bullet3;
    }

    public String getBullet4() {
        return bullet4;
    }

    public void setBullet4(String bullet4) {
        this.bullet4 = bullet4;
    }

    public String getBullet5() {
        return bullet5;
    }

    public void setBullet5(String bullet5) {
        this.bullet5 = bullet5;
    }

    public Instant getDateCreatedProduct() {
        return dateCreatedProduct;
    }

    public void setDateCreatedProduct(Instant dateCreatedProduct) {
        this.dateCreatedProduct = dateCreatedProduct;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getProductCreatorId() {
        return productCreatorId;
    }

    public void setProductCreatorId(long productCreatorId) {
        this.productCreatorId = productCreatorId;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<SellerPerson> getSellerPersons() {
        return sellerPersons;
    }

    public void setSellerPersons(Set<SellerPerson> sellerPersons) {
        this.sellerPersons = sellerPersons;
    }

    public Set<SellerIndividual> getSellerIndividuals() {
        return sellerIndividuals;
    }

    public void setSellerIndividuals(Set<SellerIndividual> sellerIndividuals) {
        this.sellerIndividuals = sellerIndividuals;
    }

    public Set<SellerLimited> getSellerLimiteds() {
        return sellerLimiteds;
    }

    public void setSellerLimiteds(Set<SellerLimited> sellerLimiteds) {
        this.sellerLimiteds = sellerLimiteds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        if (isEnabled() != product.isEnabled()) return false;
        if (getProductCreatorId() != product.getProductCreatorId()) return false;
        if (isModified() != product.isModified()) return false;
        if (!getId().equals(product.getId())) return false;
        if (!getName().equals(product.getName())) return false;
        if (!getDescription().equals(product.getDescription())) return false;
        if (!getBrand().equals(product.getBrand())) return false;
        if (!getUrlImg1().equals(product.getUrlImg1())) return false;
        if (!getUrlImg2().equals(product.getUrlImg2())) return false;
        if (!getUrlImg3().equals(product.getUrlImg3())) return false;
        if (!getUrlImg4().equals(product.getUrlImg4())) return false;
        if (!getUrlImg5().equals(product.getUrlImg5())) return false;
        if (!getUrlImg6().equals(product.getUrlImg6())) return false;
        if (!getUrlImg7().equals(product.getUrlImg7())) return false;
        if (!getUrlImg8().equals(product.getUrlImg8())) return false;
        if (!getUrlImg9().equals(product.getUrlImg9())) return false;
        if (!getKeyword1().equals(product.getKeyword1())) return false;
        if (!getKeyword2().equals(product.getKeyword2())) return false;
        if (!getKeyword3().equals(product.getKeyword3())) return false;
        if (!getKeyword4().equals(product.getKeyword4())) return false;
        if (!getKeyword5().equals(product.getKeyword5())) return false;
        if (!getBullet1().equals(product.getBullet1())) return false;
        if (!getBullet2().equals(product.getBullet2())) return false;
        if (!getBullet3().equals(product.getBullet3())) return false;
        if (!getBullet4().equals(product.getBullet4())) return false;
        if (!getBullet5().equals(product.getBullet5())) return false;
        if (!getDateCreatedProduct().equals(product.getDateCreatedProduct())) return false;
        if (!getUsers().equals(product.getUsers())) return false;
        if (!getSellerPersons().equals(product.getSellerPersons())) return false;
        if (!getSellerIndividuals().equals(product.getSellerIndividuals())) return false;
        return getSellerLimiteds().equals(product.getSellerLimiteds());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getBrand().hashCode();
        result = 31 * result + getUrlImg1().hashCode();
        result = 31 * result + getUrlImg2().hashCode();
        result = 31 * result + getUrlImg3().hashCode();
        result = 31 * result + getUrlImg4().hashCode();
        result = 31 * result + getUrlImg5().hashCode();
        result = 31 * result + getUrlImg6().hashCode();
        result = 31 * result + getUrlImg7().hashCode();
        result = 31 * result + getUrlImg8().hashCode();
        result = 31 * result + getUrlImg9().hashCode();
        result = 31 * result + getKeyword1().hashCode();
        result = 31 * result + getKeyword2().hashCode();
        result = 31 * result + getKeyword3().hashCode();
        result = 31 * result + getKeyword4().hashCode();
        result = 31 * result + getKeyword5().hashCode();
        result = 31 * result + getBullet1().hashCode();
        result = 31 * result + getBullet2().hashCode();
        result = 31 * result + getBullet3().hashCode();
        result = 31 * result + getBullet4().hashCode();
        result = 31 * result + getBullet5().hashCode();
        result = 31 * result + getDateCreatedProduct().hashCode();
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + (int) (getProductCreatorId() ^ (getProductCreatorId() >>> 32));
        result = 31 * result + (isModified() ? 1 : 0);
        result = 31 * result + getUsers().hashCode();
        result = 31 * result + getSellerPersons().hashCode();
        result = 31 * result + getSellerIndividuals().hashCode();
        result = 31 * result + getSellerLimiteds().hashCode();
        return result;
    }
}