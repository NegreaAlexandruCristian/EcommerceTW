package com.ecommerce.repositories;

import com.ecommerce.models.Password;

public interface PasswordRepository{

    void savePassword(Password password);
    Password passwordById(Long id);

}
