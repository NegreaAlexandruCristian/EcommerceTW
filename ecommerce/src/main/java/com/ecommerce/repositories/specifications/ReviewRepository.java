package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Review;

import java.util.List;

public interface ReviewRepository {

    void save(Review review, Long idProduct, Long idUser);
    void delete(Review review);
    void deleteById(Long id);
    int count();
    List<Review> findAll();
    boolean existsById(Long id);
    Review findById(Long id);


}
