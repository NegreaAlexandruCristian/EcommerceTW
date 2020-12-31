package com.ecommerce.utils;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

public class ProductBuilder {
    private String name;
    private double price;
    private int sale;
    private Category category;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder sale(int sale) {
        this.sale = sale;
        return this;
    }

    public ProductBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setSale(sale);
        product.setName(name);
        product.setDescription("Description");
        product.setPhoto("Photo");
        return product;
    }
}
