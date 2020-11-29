package com.ecommerce.repositories;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.util.CustomPasswordEncoder;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImplementation(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        Session session=sessionFactory.getCurrentSession();
        Query<User> query=session.createQuery("from User WHERE username=:user");
        query.setParameter("user",username);
        List<User> user=query.list();
        if(user.size()==0) {
            return null;
        }
        Hibernate.initialize(user.get(0).getReviews());
        return user.get(0);
    }

    @Override
    public void deleteUserByUsername(String username) {

    }


    /*
    @id is the user id from User table
     */
    @Override
    public void updateUserPassword(Long id, String password) {
        Session session = sessionFactory.getCurrentSession();
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        String cryptedPassword = customPasswordEncoder.encode(password);
        Query query = session.createQuery("UPDATE Password SET password =: cryptedPassword WHERE id=:id");
        query.setParameter("cryptedPassword",cryptedPassword);
        query.setParameter("id",id);
        query.executeUpdate();
    }

    //ok
    @Override
    @Transactional
    public User save(User user){
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    //ok
    @Override
    @Transactional
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    //nice
    @Override
    @Transactional
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id",id);
        try{
            query.getSingleResult();
        }catch (NoResultException e){
            throw new NotFoundException();
        }
        return true;
    }

    //ok
    @Override
    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.list();
    }

    //ok
    @Override
    @Transactional
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.list().size();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("DELETE FROM User WHERE id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE id=:id");
        Long id =  user.getId();
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
