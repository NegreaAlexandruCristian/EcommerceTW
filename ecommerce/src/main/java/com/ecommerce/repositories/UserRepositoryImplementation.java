package com.ecommerce.repositories;

import com.ecommerce.exceptions.ConstraintViolationExceptionCustom;
import com.ecommerce.exceptions.ExistentException;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;
import com.ecommerce.util.CustomPasswordEncoder;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    private final SessionFactory sessionFactory;
    private final CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

    @Autowired
    public UserRepositoryImplementation(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        Session session=sessionFactory.getCurrentSession();
        Query<User> query=session.createQuery("from User WHERE username=:user");
        query.setParameter("user",username);
        List<User> users =query.list();
        if(users.size()==0) {
            throw new NotFoundException();
        }
        return users.get(0);
    }

    /*
    @id is the user id from User table
     */
    @Override
    public void updateUserPassword(Password password) {
        Session session = sessionFactory.getCurrentSession();
        Password currentPassword = session.get(Password.class, password.getId());
        if(currentPassword == null){
            throw new NotFoundException();
        }
        if(verifyPasswords(currentPassword, password)) {
            throw new ExistentException();
        }
        String cryptedPassword = customPasswordEncoder.encode(password.getPassword());
        currentPassword.setOldPassword(currentPassword.getPassword());
        currentPassword.setPassword(cryptedPassword);
        session.update(currentPassword);
    }

    @Override
    public void updateUserInformation(UserInformation userInformation) {
        Session session = sessionFactory.getCurrentSession();
        UserInformation userInfo = session.get(UserInformation.class, userInformation.getId());
        updateUserInfo(userInfo, userInformation);
        session.update(userInfo);
    }

    private void updateUserInfo(UserInformation currentUserInformation, UserInformation userInformation) {
        currentUserInformation.setEmail(userInformation.getEmail());
        currentUserInformation.setFirstName(userInformation.getFirstName());
        currentUserInformation.setLastName(userInformation.getLastName());
        currentUserInformation.setPhone(userInformation.getPhone());
    }

    private boolean verifyPasswords(Password currentPassword, Password password) {
        return customPasswordEncoder.matches(password.getPassword(), currentPassword.getPassword()) ||
                customPasswordEncoder.matches(password.getPassword(), currentPassword.getOldPassword());
    }

    //ok
    @Override
    public User save(User user){
        Session session=sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(user);
        } catch (ConstraintViolationException e){
            throw new ConstraintViolationExceptionCustom();
        }
        return user;
    }

    //ok
    @Override
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user == null){
            throw new NotFoundException();
        }
        init(user);
        return session.get(User.class, id);
    }

    private void init(User user){
        Hibernate.initialize(user.getReviews());
        Hibernate.initialize(user.getHistoryList());
        Hibernate.initialize(user.getCartList());
        Hibernate.initialize(user.getUserWishlists());
        Hibernate.initialize(user.getUserCreditCards());
        Hibernate.initialize(user.getUserAddresses());
    }

    //nice
    @Override
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
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.list();
    }

    //ok
    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        return query.list().size();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        user = session.get(User.class, user.getId());
        session.delete(user);
    }
}
