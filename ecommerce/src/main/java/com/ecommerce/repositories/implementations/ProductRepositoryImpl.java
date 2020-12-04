package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.ProductFilter;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.util.Filter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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

        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("FROM Category WHERE name =: name");
        query.setParameter("name", product.getCategory().getName());
        Category category;

        try{
            category = query.getSingleResult();
        }catch (NoResultException r){
            throw new NotFoundException();
        }

        product.setCategory(category);
        session.saveOrUpdate(product);

        return product;
    }

    private void init(Product product){
        Hibernate.initialize(product.getReviewList());
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
            product.setReviewList(new ArrayList<>());
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

    @Override
    public List<Product> filter(ProductFilter productFilter){
        List<Product> products = this.findAll();
        return Filter.filterBuilder(products)
                .filterCategory(productFilter.getCategory())
                .filterRating(productFilter.getRating())
                .filterPrice(productFilter.getPrice())
                .sortPrice(productFilter.getPriceAscendingOrDescending())
                .collect();
    }
}
