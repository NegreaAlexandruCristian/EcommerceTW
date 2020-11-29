package com.ecommerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "history")
public class History {

    @Id
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "buy_date")
    private LocalDate buyDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userHistory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_history",
            joinColumns = @JoinColumn(name = "history_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productsHistory;

}
