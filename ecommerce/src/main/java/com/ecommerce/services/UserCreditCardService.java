package com.ecommerce.services;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardService {

    UserCreditCard findById(Long id);
    void save(UserCreditCard userCreditCard, Long id);
    boolean existsById(Long id);
    List<UserCreditCard> findAll();
    int count();
    void deleteById(Long id);
    void delete(Long idUser, Long id);
    UserCreditCard findUsersCreditCard(Long idUser, Long id);
}