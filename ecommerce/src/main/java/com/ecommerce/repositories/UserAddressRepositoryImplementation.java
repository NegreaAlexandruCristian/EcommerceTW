package com.ecommerce.repositories;

import com.ecommerce.models.UserAddress;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UserAddressRepositoryImplementation implements UserAddressRepository{

    private final SessionFactory sessionFactory;

    @Autowired
    public UserAddressRepositoryImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserAddress findUsersAddresses(Long idUser, Long id) {
        return null;
    }

    @Override
    public void save(UserAddress userAddress, Long id) {

    }

    @Override
    public UserAddress findById(Long id) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public List<UserAddress> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Long idUser, Long id) {

    }
}
