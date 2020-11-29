package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(value= {"reviews,historyList"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Password password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserInformation userInformation;

    @NotNull
    @Column(name = "username", unique = true)
    @NotBlank(message = "Please enter your username!")
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "userHistory")
    private List<History> historyList;

    @OneToMany(mappedBy = "user")
    private List<UserCreditCard> userCreditCards;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;

    @OneToMany(mappedBy = "user")
    private List<Cart> cartList;

    @OneToMany(mappedBy = "user")
    private List<UserWishlist> userWishlists;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public List<UserCreditCard> getUserCreditCards() {
        return userCreditCards;
    }

    public void setUserCreditCards(List<UserCreditCard> userCreditCards) {
        this.userCreditCards = userCreditCards;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password=" + password +
                ", userInformation=" + userInformation +
                ", username='" + username + '\'' +
                ", reviews=" + reviews +
                ", historyList=" + historyList +
                ", userCreditCards=" + userCreditCards +
                ", userAddresses=" + userAddresses +
                ", cartList=" + cartList +
                '}';
    }

}
