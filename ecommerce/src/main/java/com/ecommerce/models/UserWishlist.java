package com.ecommerce.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_wishlist")
public class UserWishlist {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinColumn(name = "product_id")
    @JoinTable(name = "product_wishlist",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product;
}
