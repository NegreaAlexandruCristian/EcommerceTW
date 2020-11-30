package com.ecommerce.services;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardService {

    UserCreditCard findById(Long id);
    UserCreditCard save(UserCreditCard userCreditCard);
    boolean existsById(Long id);
    List<UserCreditCard> findAll();
    int count();
    void deleteById(Long id);
    void delete(UserCreditCard userCreditCard);
    UserCreditCard findUsersCreditCard(Long idUser, Long id);
}