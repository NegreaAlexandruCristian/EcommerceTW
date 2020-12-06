package com.ecommerce.repositories.implementations;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        User user = session.get(User.class, id);
        Hibernate.initialize(user.getUserCreditCards());
        creditCard.setId(null);
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

    private void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserCreditCard creditCard = session.get(UserCreditCard.class, id);
        session.delete(creditCard);
    }

    @Override
    public void delete(Long idUser, Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, idUser);
        Hibernate.initialize(user.getUserCreditCards());
        UserCreditCard userCreditCard = session.get(UserCreditCard.class, id);
        List<UserCreditCard> list = user.getUserCreditCards();
        list.remove(userCreditCard);
        deleteById(id);
        session.saveOrUpdate(user);
    }

    @Override
    public List<UserCreditCard> findUserCreditCards(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        Hibernate.initialize(user.getUserCreditCards());
        return user.getUserCreditCards();
    }
}
