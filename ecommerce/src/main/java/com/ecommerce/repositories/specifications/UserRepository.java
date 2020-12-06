package com.ecommerce.repositories.specifications;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

import java.util.List;

public interface UserRepository extends RepositoryManager<User,Long>{

    User findByUsername(String username);
    default List<User> findAll() {
        return null;
    }

    default int count(){
        return 0;
    }

    void updateUserPassword(Password password);
    void updateUserInformation(UserInformation userInformation);
}
