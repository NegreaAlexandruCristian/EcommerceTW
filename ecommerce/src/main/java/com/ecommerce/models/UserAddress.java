package com.ecommerce.models;

import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "country")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @NotBlank(message = "A country should be inserted")
    private String country;

    @NotNull
    @Column(name = "city")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @NotBlank(message = "A city should be inserted")
    private String city;

    @NotNull
    @Column(name = "address")
    @NotBlank(message = "An address should be inserted")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userAddresses")
    private User user;

    public UserAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
