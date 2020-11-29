package com.ecommerce.models;

import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Value("1")
    @NotBlank(message = "Should at least be one")
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cartList")
    private User user;

    @ManyToMany
    @JoinTable(name = "product_cart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsCart;

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(List<Product> productsCart) {
        this.productsCart = productsCart;
    }
}
