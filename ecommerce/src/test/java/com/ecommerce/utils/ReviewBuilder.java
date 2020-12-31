package com.ecommerce.utils;

import com.ecommerce.models.Review;
import com.ecommerce.models.UserAddress;

import java.time.LocalDate;

public class ReviewBuilder {

    private double review;
    private String comment;
    private LocalDate localDate;

    public static ReviewBuilder builder() {
        return new ReviewBuilder();
    }

    public ReviewBuilder review(double review) {
        this.review = review;
        return this;
    }

    public ReviewBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public ReviewBuilder localDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public Review build() {
        Review userReview = new Review();
        userReview.setReview(review);
        userReview.setComment(comment);
        userReview.setReviewDate(localDate);
        return userReview;
    }
}
