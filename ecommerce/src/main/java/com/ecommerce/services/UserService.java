package com.ecommerce.services;

import com.ecommerce.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    User save(User user);
    Boolean existsById(Long id);
    List<User> findAll();
    int count();
    void deleteById(Long id);
    void delete(User user);

}
