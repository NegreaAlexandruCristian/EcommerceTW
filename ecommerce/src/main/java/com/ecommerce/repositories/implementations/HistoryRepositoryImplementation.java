package com.ecommerce.repositories.implementations;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.CompositePK;
import com.ecommerce.models.HistoryItems;
import com.ecommerce.repositories.specifications.HistoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class HistoryRepositoryImplementation implements HistoryRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HistoryRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HistoryItems> findHistoryItemsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<HistoryItems> query = session.createQuery("FROM HistoryItems WHERE userId =: userId");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public void addProductToHistory(CartItems cartItem) {
        Session session = sessionFactory.getCurrentSession();
        HistoryItems historyItem = convertFromCartToHistory(cartItem);
        session.saveOrUpdate(historyItem);
    }

    @Override
    public void deleteHistoryForUser(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        List<HistoryItems> historyItems = findHistoryItemsByUserId(userId);
        historyItems.forEach(session::delete);
    }

    @Override
    public void deleteHistoryItem(Long userId, Long productId) {
        Session session = sessionFactory.getCurrentSession();
        HistoryItems historyItem = session.get(HistoryItems.class, new CompositePK(userId, productId));
        session.delete(historyItem);
    }

    private HistoryItems convertFromCartToHistory(CartItems cartItem) {
        HistoryItems historyItem = new HistoryItems();
        historyItem.setUserId(cartItem.getUserId());
        historyItem.setProductId(cartItem.getProductId());
        historyItem.setQuantity(cartItem.getQuantity());
        historyItem.setPurchaseDate(LocalDate.now());
        return historyItem;
    }


}
