package com.ecommerce.repositories.specifications;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardRepository{

    void save(UserCreditCard creditCard, Long id);
    void delete(Long idUser, Long id);
    List<UserCreditCard> findUserCreditCards(Long userId);
    UserCreditCard findById(Long id);
    boolean existsById(Long id);
}
