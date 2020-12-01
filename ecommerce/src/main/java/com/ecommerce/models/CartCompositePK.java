package com.ecommerce.models;

import java.io.Serializable;

public class CartCompositePK implements Serializable {
    private Long userId;
    private Long productId;

    public CartCompositePK(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartCompositePK() {
    }
}
