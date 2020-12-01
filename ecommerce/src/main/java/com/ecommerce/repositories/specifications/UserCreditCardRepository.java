package com.ecommerce.repositories.specifications;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardRepository{

    UserCreditCard findUsersCreditCard(Long idUser, Long id);
    void save(UserCreditCard creditCard, Long id);
    UserCreditCard findById(Long id);
    boolean existsById(Long id);
    List<UserCreditCard> findAll();
    int count();
    void deleteById(Long id);
    void delete(Long idUser, Long id);
}
