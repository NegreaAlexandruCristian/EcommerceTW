package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCreditCardRepositoryImplementation implements UserCreditCardRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserCreditCardRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserCreditCard creditCard, Long id) {
        Session session=sessionFactory.getCurrentSession();
        creditCard.setId(null);
        User user = session.get(User.class, id);
        user.addCreditCard(creditCard);
        session.saveOrUpdate(user);
    }

    @Override
    public UserCreditCard findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        if (creditCard == null) {
            throw new NotFoundException();
        }
        return creditCard;
    }

    @Override
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        return creditCard != null;
    }

    @Override
    public List<UserCreditCard> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard");
        return query.list();
    }

    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard");
        return query.list().size();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        session.delete(creditCard);
    }

    @Override
    public void delete(Long idUser, Long id) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, idUser);
        UserCreditCard userCreditCard = session.get(UserCreditCard.class, id);
        List<UserCreditCard> list = user.getUserCreditCards();
        list.remove(userCreditCard);
        Query query = session.createQuery("DELETE FROM UserCreditCard WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        user.setUserCreditCards(list);
        session.saveOrUpdate(user);

    }

    @Override
    public UserCreditCard findUsersCreditCard(Long idUser, Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, idUser);
        UserCreditCard userCreditCard = session.get(UserCreditCard.class, id);
        List<UserCreditCard> userCreditCards = user.getUserCreditCards();

        if(userCreditCards.contains(userCreditCard)){

            return userCreditCard;
        }

        return null;
    }
}
