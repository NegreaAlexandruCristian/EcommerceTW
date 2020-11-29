package com.ecommerce.repositories;

import com.ecommerce.models.User;

public interface UserRepository extends RepositoryManager<User,Long>{

    User findByUsername(String username);
    void deleteUserByUsername(String username);
}
