package com.ecommerce.services;

import com.ecommerce.models.User;

public interface UserService {

    User findById(Long id);
    User save(User user);

}
