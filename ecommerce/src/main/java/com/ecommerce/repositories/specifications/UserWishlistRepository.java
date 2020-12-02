package com.ecommerce.repositories.specifications;

import com.ecommerce.models.UserWishlist;

import java.util.List;

public interface UserWishlistRepository {
    List<UserWishlist> findWishlistItemsByUserId(Long userId);
    //TODO modify to product
    UserWishlist findProductById(Long userId, Long productId);
    void addProductToWishlist(Long userId, Long productId);
    void deleteWishlistItems(Long userId);
    void deleteWishlistItem(Long userId, Long productId);
}
