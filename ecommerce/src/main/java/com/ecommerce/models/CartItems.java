package com.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cart_items")
@IdClass(CompositePK.class)
public class CartItems implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItems)) return false;
        CartItems cartItems = (CartItems) o;
        return Objects.equals(userId, cartItems.userId) && Objects.equals(productId, cartItems.productId) && Objects.equals(quantity, cartItems.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, quantity);
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
