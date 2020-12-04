package com.ecommerce.models;

public class ProductFilter {

    private Category category;
    private double price;
    private String priceAscendingOrDescending = "ASC";
    private double rating;

    public ProductFilter() {
    }

    public String getPriceAscendingOrDescending() {
        return priceAscendingOrDescending;
    }

    public void setPriceAscendingOrDescending(String priceAscendingOrDescending) {
        this.priceAscendingOrDescending = priceAscendingOrDescending;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}
