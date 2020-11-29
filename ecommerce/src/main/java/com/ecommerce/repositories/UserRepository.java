package com.ecommerce.repositories;

import com.ecommerce.models.User;

import java.util.List;

public interface UserRepository extends RepositoryManager<User,Long>{

    User findByUsername(String username);
    void deleteUserByUsername(String username);
    void updateUserPassword(Long id, String password);
}
