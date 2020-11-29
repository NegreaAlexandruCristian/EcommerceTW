package com.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Password password;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private UserInformation userInformation;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

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

//    public UserInformation getUserInformation() {
//        return userInformation;
//    }
//
//    public void setUserInformation(UserInformation userInformation) {
//        this.userInformation = userInformation;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password=" + password +
               // ", userInformation=" + userInformation +
                ", username='" + username + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
