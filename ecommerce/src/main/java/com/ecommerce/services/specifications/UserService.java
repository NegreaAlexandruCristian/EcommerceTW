package com.ecommerce.services.specifications;

import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;

import java.util.List;

public interface UserService {

    User findById(Long id);
    Boolean existsById(Long id);
    User save(User user);
    List<User> findAll();
    User findByUsername(String username);
    int count();
    void deleteById(Long id);
    void delete(User user);
    void updatePassword(Password password);
    void updateUserInformation(UserInformation userInformation);

}