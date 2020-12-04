package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;

import java.util.List;

public interface ProductRepository extends RepositoryManager<Product, Long> {

    public List<Product> filter(ProductFilter productFilter);
}
