package com.ecommerce.util;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;

import java.util.List;

public class ProductBuilder {
    private String name;
    private double price;
    private int sale;
    private String url;
    private String description;
    private Category category;
    private int quantity;
    private List<Review> reviewList;

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

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ProductBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder reviewList(List<Review> reviews){
        this.reviewList = reviews;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setSale(sale);
        product.setName(name);
        product.setDescription(description);
        product.setPhoto(url);
        product.setQuantity(quantity);
        product.setReviewList(this.reviewList);

        return product;
    }
}
