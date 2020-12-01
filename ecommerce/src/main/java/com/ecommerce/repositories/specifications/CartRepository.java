package com.ecommerce.repositories.specifications;

import com.ecommerce.models.CartItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository {
    List<CartItems> findCartItemsByUserId(Long userId);
    CartItems addProductToCart(Long userId, Long productId);
}
