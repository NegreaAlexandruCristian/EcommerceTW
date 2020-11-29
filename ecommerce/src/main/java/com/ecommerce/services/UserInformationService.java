package com.ecommerce.services;

import com.ecommerce.models.Password;
import com.ecommerce.models.UserInformation;

public interface UserInformationService {

    UserInformation findById(Long id);
    UserInformation save(UserInformation userInformation);
}
