package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Review;

public interface ReviewRepository extends RepositoryManager<Review, Long>{

    void save(Review review, Long idProduct, Long idUser);

    @Override
    default <S extends Review> S save(S entity) {
        return null;
    }
}
