package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "products")
@JsonIgnoreProperties({"reviewList, userWishlist, histories, cartList"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @NotNull
    @Column(name = "price")
    private double price;

    @Value("1")
    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Value("0")
    @NotNull
    @Column(name = "sale")
    private int sale;

    @NotNull
    @Column(name = "photo")
    private String photo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_review")
    private List<Review> reviewList;

    @ManyToMany
    @JoinTable(name = "product_wishlist",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id"))
    private List<UserWishlist> userWishlist;

    @ManyToMany
    @JoinTable(name = "product_history",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "history_id"))
    private List<History> histories;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<UserWishlist> getUserWishlist() {
        return userWishlist;
    }

    public void setUserWishlist(List<UserWishlist> userWishlist) {
        this.userWishlist = userWishlist;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sale=" + sale +
                ", photo='" + photo + '\'' +
                ", category=" + category +
                ", reviewList=" + reviewList +
                ", userWishlist=" + userWishlist +
                ", histories=" + histories +
                '}';
    }

    public void addReview(Review review) {

        reviewList.add(review);
    }
}
