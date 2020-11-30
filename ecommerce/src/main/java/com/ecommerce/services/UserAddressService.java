package com.ecommerce.services;

import com.ecommerce.models.UserAddress;

import java.util.List;

public interface UserAddressService{

    UserAddress findUsersAddresses(Long idUser, Long id);
    void save(UserAddress userAddress, Long id);
    UserAddress findById(Long id);
    boolean existsById(Long id);
    List<UserAddress> findAll();
    int count();
    void deleteById(Long id);
    void delete(Long idUser, Long id);
}
