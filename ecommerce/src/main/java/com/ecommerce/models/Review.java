package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Value("0")
    @Column(name = "review")
    private int review;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "review_date")
    private LocalDate review_date;

    @ManyToOne
    @JoinColumn(name = "reviews")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reviewList")
    private Product product;

    public Review(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReview_date() {
        return review_date;
    }

    public void setReview_date(LocalDate review_date) {
        this.review_date = review_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
