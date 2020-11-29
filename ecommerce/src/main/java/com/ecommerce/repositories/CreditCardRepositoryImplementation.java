package com.ecommerce.repositories;

import com.ecommerce.exceptions.ConstraintViolationExceptionCustom;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository
public class CreditCardRepositoryImplementation implements CrediCardRepository{

    private final SessionFactory sessionFactory;

    @Autowired
    public CreditCardRepositoryImplementation(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public UserCreditCard save(UserCreditCard creditCard) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(creditCard);

        } catch (ConstraintViolationException e){
            throw new ConstraintViolationExceptionCustom();
        }
        return creditCard;
    }

    @Override
    @Transactional
    public UserCreditCard findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard WHERE id=:id");
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard WHERE id=:id");
        query.setParameter("id",id);
        try{
            query.getSingleResult();
        }catch (NoResultException e){
            throw new NotFoundException();
        }
        return true;
    }

    @Override
    @Transactional
    public List<UserCreditCard> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard");
        return query.list();
    }

    @Override
    @Transactional
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("FROM UserCreditCard");
        return query.list().size();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("DELETE FROM UserCreditCard WHERE id=:id");
        query.setParameter("id",id);
        query.executeUpdate();

    }

    @Override
    @Transactional
    public void delete(UserCreditCard userCreditCard) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserCreditCard> query = session.createQuery("DELETE FROM UserCreditCard WHERE id=:id");
        Long id =  userCreditCard.getId();
        query.setParameter("id",id);
        query.executeUpdate();

    }
}
