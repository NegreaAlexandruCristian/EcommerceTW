package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.ConstraintViolationExceptionCustom;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product save(Product product) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(product);
            return product;
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationExceptionCustom();
        }
    }

    private void init(Product product){
        Hibernate.initialize(product.getReviewList());
        Hibernate.initialize(product.getHistories());
        Hibernate.initialize(product.getCartList());
        Hibernate.initialize(product.getUserWishlist());
    }

    @Override
    public Product findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null){
            throw new NotFoundException();
        }
        init(product);
        return product;
    }

    @Override
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product != null;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product");
        List<Product> products = query.list();
        products.forEach((product -> {
            product.setCartList(new ArrayList<>());
            product.setReviewList(new ArrayList<>());
            product.setHistories(new ArrayList<>());
            product.setUserWishlist(new ArrayList<>());
        }));
        return products;
    }

    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product");
        return query.list().size();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product currentProduct = session.get(Product.class, id);
        session.delete(currentProduct);
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Product dbProduct = session.get(Product.class, product.getId());
        session.delete(dbProduct);
    }
}
