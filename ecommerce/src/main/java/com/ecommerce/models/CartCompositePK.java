package com.ecommerce.models;

import java.io.Serializable;
import java.util.Objects;

public class CartCompositePK implements Serializable {
    private Long userId;
    private Long productId;

    public CartCompositePK(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartCompositePK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartCompositePK)) return false;
        CartCompositePK that = (CartCompositePK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
