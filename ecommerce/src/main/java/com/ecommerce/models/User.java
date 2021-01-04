package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_review")
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserAddress> userAddresses;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Password password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserInformation userInformation;

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

    public void setReviews(List<Review> reviewList) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews = reviewList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public void addUserAddress(UserAddress userAddress) {
        if (this.userAddresses == null) {
            this.userAddresses = new ArrayList<>();
        }
        this.userAddresses.add(userAddress);
    }

    public void removeAddress(UserAddress address) {
        this.userAddresses.remove(address);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void clearAddresses() {
        this.userAddresses.clear();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", reviews=" + reviews +
                ", userAddresses=" + userAddresses +
                '}';
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }
}
