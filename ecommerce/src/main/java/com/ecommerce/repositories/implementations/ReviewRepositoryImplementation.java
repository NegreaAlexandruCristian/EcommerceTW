package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.ConstraintViolationExceptionCustom;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.repositories.specifications.ReviewRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository
public class ReviewRepositoryImplementation implements ReviewRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ReviewRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Review findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Review review = session.get(Review.class, id);
        if (review == null) {
            throw new NotFoundException();
        }
        return review;
    }

    @Override
    public List<Review> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Review> query = session.createQuery("FROM Review");
        return query.list();
    }

    @Override
    public void deleteById(Long id, Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        Product product = session.get(Product.class, productId);
        Review review = session.get(Review.class,id);
        if(user==null || product == null || review == null){
            throw new NotFoundException();
        }

        Hibernate.initialize(user.getReviews());
        Hibernate.initialize(product.getReviewList());
        if(!user.getReviews().contains(review) || !product.getReviewList().contains(review)){
            throw new NotFoundException();
        }
        user.removeReview(review);
        product.removeReview(review);

        session.delete(review);
    }


    @Override
    public void save(Review review, Long idProduct, Long idUser) {
        Session session=sessionFactory.getCurrentSession();
        try {
            Product product = session.get(Product.class, idProduct);
            User user = session.get(User.class, idUser);
            Hibernate.initialize(user.getReviews());

            product.addReview(review);
            user.addReview(review);

            session.saveOrUpdate(review);

        } catch (ConstraintViolationException e){
            throw new ConstraintViolationExceptionCustom();
        }
    }
}
