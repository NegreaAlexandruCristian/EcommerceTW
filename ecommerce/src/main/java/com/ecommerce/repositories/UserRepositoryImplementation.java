package com.ecommerce.repositories;

import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.util.CustomPasswordEncoder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    final private SessionFactory sessionFactory;

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

    @Override
    @Transactional
    public User getUser(Long id){
        Session session=sessionFactory.getCurrentSession();
        User user=session.get(User.class,id);
        if(user==null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getUsers(){
        Session session=sessionFactory.getCurrentSession();
        Query<User> query=session.createQuery("from User");
        return query.list();
    }

    @Override
    @Transactional
    public User save(User user){
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public boolean existsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id",id);
        User user = query.getSingleResult();
        return user!=null;
    }

    @Override
    @Transactional
    public Iterable<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public long count() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.getResultList().size();
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

    @Override
    @Transactional
    public void deleteAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
    }
}
