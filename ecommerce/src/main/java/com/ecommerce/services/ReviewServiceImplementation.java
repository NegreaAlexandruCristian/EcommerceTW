package com.ecommerce.services;

import com.ecommerce.models.Review;
import com.ecommerce.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id);
    }

    @Override
    public void save(Review review, Long idProduct, Long idUser) {
        reviewRepository.save(review, idProduct, idUser);
    }

    @Override
    public boolean existsById(Long id) {
        return this.reviewRepository.existsById(id);
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public int count() {
        return this.reviewRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }

    @Override
    public void delete(Review review) {
        this.reviewRepository.delete(review);
    }
}
