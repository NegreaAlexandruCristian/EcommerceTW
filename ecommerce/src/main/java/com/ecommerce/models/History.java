package com.ecommerce.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "history")
public class History implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "buy_date")
    private LocalDate buyDate;

    @Column(name = "quantity")
    @NotNull
    @Value("1")
    private int quantity;

    @NotNull
    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "historyList")
    private User userHistory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_history",
            joinColumns = @JoinColumn(name = "history_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsHistory;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(User userHistory) {
        this.userHistory = userHistory;
    }

    public List<Product> getProductsHistory() {
        return productsHistory;
    }

    public void setProductsHistory(List<Product> productsHistory) {
        this.productsHistory = productsHistory;
    }
}
