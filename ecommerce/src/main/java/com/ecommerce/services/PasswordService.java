package com.ecommerce.services;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;

public interface PasswordService {

    Password findById(Long id);
    void save(Password password);
}
