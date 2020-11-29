package com.ecommerce.repositories;

import com.ecommerce.models.UserInformation;


public interface UserInformationRepository extends RepositoryManager<UserInformation, Long>{

    UserInformation findByEmail(String email);
    UserInformation findByPhone(String phone);
}
