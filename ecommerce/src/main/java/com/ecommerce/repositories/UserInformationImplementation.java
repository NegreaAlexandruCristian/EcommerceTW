package com.ecommerce.repositories;

import com.ecommerce.models.UserInformation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class UserInformationImplementation implements UserInformationRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserInformationImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public UserInformation findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("FROM UserInformation where email =:email");
        query.setParameter("email",email);
        List<UserInformation> userInfo = query.getResultList();
        return userInfo.get(0);
    }

    @Override
    @Transactional
    public UserInformation findByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("FROM UserInformation where phone =:phone");
        query.setParameter("phone",phone);
        List<UserInformation> userInfo = query.getResultList();
        return userInfo.get(0);
    }

    @Override
    @Transactional
    public <S extends UserInformation> S save(S userInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(userInfo);
        return userInfo;
    }

    @Override
    @Transactional
    public UserInformation findById(Long user_id) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("FROM UserInformation where user_id =:id");
        query.setParameter("id",user_id);
        List<UserInformation> userInfo = query.getResultList();
        return userInfo.get(0);
    }

    @Override
    @Transactional
    public boolean existsById(Long user_id) {
        UserInformation userInfo = findById(user_id);
        return userInfo == null;
    }

    @Override
    @Transactional
    public Iterable<UserInformation> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("FROM UserInformation");
        return query.getResultList();
    }

    @Override
    @Transactional
    public long count() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("FROM UserInformation");
        return query.getResultList().size();
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        UserInformation userInfo = findById(userId);
        session.delete(userInfo);
    }

    @Override
    @Transactional
    public void delete(UserInformation userInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(userInfo);
    }

    @Override
    @Transactional
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserInformation> query = session.createQuery("DELETE FROM UserInformation");
        query.executeUpdate();
    }
}
