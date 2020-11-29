package com.ecommerce.repositories;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

import java.util.List;

public interface ProductRepository{

    Product findByName(String name);
    List<Product> findByCategory(Category category);

//    @Query("FROM Product inner join review ON product = review")
//    List<Review> getReviews(@Param("product_id") Long id);
}
