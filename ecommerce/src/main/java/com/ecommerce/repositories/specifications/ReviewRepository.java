package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Review;

import java.util.List;

public interface ReviewRepository{

    void save(Review review, Long idProduct, Long idUser);
    Review findById(Long id);
    List<Review> findAll();
    void deleteById(Long id, Long userId, Long productId);
}
