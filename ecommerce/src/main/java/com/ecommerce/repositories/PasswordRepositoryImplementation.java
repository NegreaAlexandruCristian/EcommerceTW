package com.ecommerce.repositories;

import com.ecommerce.models.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import javax.transaction.Transactional;

@Repository
public class PasswordRepositoryImplementation implements PasswordRepository{

    final private SessionFactory sessionFactory;

    @Autowired
    public PasswordRepositoryImplementation(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void savePassword(Password password) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(password);
    }

    @Override
    @Transactional
    public Password passwordById(Long id) {
        Session session=sessionFactory.getCurrentSession();
        Query<Password> passwordQuery = session.createQuery("FROM Password WHERE Password.id=:id");
        passwordQuery.setParameter("id",id);

        return passwordQuery.getSingleResult();
    }
}
