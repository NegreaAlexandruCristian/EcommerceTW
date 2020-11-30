package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties({"reviews,historyList"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    @NotBlank(message = "Please enter your username!")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_review")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "userHistory", fetch = FetchType.LAZY)
    private Set<History> historyList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserCreditCard> userCreditCards;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserAddress> userAddresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Cart> cartList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Password password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserInformation userInformation;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserWishlist> userWishlists;

    public User() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(Set<History> historyList) {
        this.historyList = historyList;
    }

    public Set<UserCreditCard> getUserCreditCards() {
        return userCreditCards;
    }

    public void setUserCreditCards(Set<UserCreditCard> userCreditCards) {
        this.userCreditCards = userCreditCards;
    }

    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public Set<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(Set<Cart> cartList) {
        this.cartList = cartList;
    }

    public Set<UserWishlist> getUserWishlists() {
        return userWishlists;
    }

    public void setUserWishlists(Set<UserWishlist> userWishlists) {
        this.userWishlists = userWishlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", reviews=" + reviews +
                ", historyList=" + historyList +
                ", userCreditCards=" + userCreditCards +
                ", userAddresses=" + userAddresses +
                ", cartList=" + cartList +
                '}';
    }

}
