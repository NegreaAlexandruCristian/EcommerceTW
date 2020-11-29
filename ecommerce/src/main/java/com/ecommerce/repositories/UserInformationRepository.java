package com.ecommerce.repositories;

import com.ecommerce.models.UserInformation;


public interface UserInformationRepository{

    UserInformation findByEmail(String email);
    UserInformation findByPhone(String phone);
}
