package com.ecommerce.services.specifications;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    void delete(Product product);
    boolean checkIfExists(Long id);
    List<Product> findAll();
    Integer count();
    void deleteById(Long id);
    List<Product> filterProducts(ProductFilter productFilter);
}
