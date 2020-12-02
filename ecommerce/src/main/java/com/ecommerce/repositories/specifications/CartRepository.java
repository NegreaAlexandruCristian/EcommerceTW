package com.ecommerce.repositories.specifications;

import com.ecommerce.models.CartItems;

import java.util.List;

public interface CartRepository {
    List<CartItems> findCartItemsByUserId(Long userId);
    CartItems addProductToCart(Long userId, Long productId);
    void deleteCartItems(Long userId);
    void deleteCartItem(Long userId, Long productId);
}
