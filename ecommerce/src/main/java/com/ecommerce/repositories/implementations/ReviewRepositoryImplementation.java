package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.ConstraintViolationExceptionCustom;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.repositories.specifications.ReviewRepository;
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
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Review review = session.get(Review.class, id);
        return review != null;
    }

    @Override
    public List<Review> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Review> query = session.createQuery("FROM Review");
        return query.list();
    }

    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<Review> query = session.createQuery("FROM Review");
        return query.list().size();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Review review = session.get(Review.class, id);
        session.delete(review);
    }

    @Override
    public void delete(Review review) {
        deleteById(review.getId());
    }

    @Override
    public void save(Review review, Long idProduct, Long idUser) {
        Session session=sessionFactory.getCurrentSession();
        try {
            Product product = session.get(Product.class, idProduct);
            User user = session.get(User.class, idUser);

            product.addReview(review);
            user.addReview(review);

            session.saveOrUpdate(user);
            session.saveOrUpdate(product);

        } catch (ConstraintViolationException e){
            throw new ConstraintViolationExceptionCustom();
        }
    }
}
