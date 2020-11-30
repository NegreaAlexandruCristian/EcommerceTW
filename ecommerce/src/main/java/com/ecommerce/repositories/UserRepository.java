package com.ecommerce.repositories;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

import java.util.List;

public interface UserRepository extends RepositoryManager<User,Long>{

    User findByUsername(String username);
    void updateUserPassword(Password password);
}
