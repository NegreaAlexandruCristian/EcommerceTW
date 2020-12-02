package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.CompositePK;
import com.ecommerce.models.UserWishlist;
import com.ecommerce.repositories.specifications.UserWishlistRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserWishlistRepositoryImpl implements UserWishlistRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserWishlistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<UserWishlist> findWishlistItemsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserWishlist> query = session.createQuery("FROM UserWishlist Where userId =: userId");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public UserWishlist findProductById(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = session.get(UserWishlist.class, new CompositePK(userId, productId));
        if(userWishlist == null) {
            throw new NotFoundException();
        }
        return userWishlist;
    }

    @Override
    public void addProductToWishlist(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = new UserWishlist();
        userWishlist.setUserId(userId);
        userWishlist.setProductId(productId);
        session.saveOrUpdate(userWishlist);
    }

    @Override
    public void deleteWishlistItems(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        List<UserWishlist> userWishlists = findWishlistItemsByUserId(userId);
        userWishlists.forEach(session::delete);
    }

    @Override
    public void deleteWishlistItem(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        UserWishlist userWishlist = session.get(UserWishlist.class, new CompositePK(userId, productId));
        if(userWishlist == null) {
            throw new NotFoundException();
        }
        session.delete(userWishlist);
    }
}
