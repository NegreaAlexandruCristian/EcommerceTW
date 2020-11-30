package com.ecommerce.services;

import com.ecommerce.models.Review;

import java.util.List;

public interface ReviewService {

    Review findById(Long id);
    void save(Review review, Long idProduct, Long idUser);
    boolean existsById(Long id);
    List<Review> findAll();
    int count();
    void deleteById(Long id);
    void delete(Review review);
}
