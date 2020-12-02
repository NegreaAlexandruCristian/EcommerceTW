package com.ecommerce.repositories.implementations;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.CompositePK;
import com.ecommerce.repositories.specifications.CartRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryImplementation implements CartRepository {

    private final SessionFactory sessionFactory;

    public CartRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CartItems> findCartItemsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItems> query = session.createQuery("FROM CartItems WHERE userId =: id");
        query.setParameter("id", userId);
        return query.list();
    }

    @Override
    public CartItems addProductToCart(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        CartItems cartItem = new CartItems();

        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(1L);

        CartItems maybeCartItem = session.get(CartItems.class, new CompositePK(userId, productId));
        if(maybeCartItem == null) {
            session.save(cartItem);
        } else {
            maybeCartItem.setQuantity(maybeCartItem.getQuantity() + 1);
            session.saveOrUpdate(maybeCartItem);

            return maybeCartItem;
        }

        return cartItem;
    }

    @Override
    public void deleteCartItems(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItems> query = session.createQuery("DELETE FROM CartItems WHERE userId =: id");
        query.setParameter("id", userId);
        query.executeUpdate();
    }

    @Override
    public void deleteCartItem(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        CartItems cartItem = session.get(CartItems.class, new CompositePK(userId, productId));
        session.delete(cartItem);
    }
}
