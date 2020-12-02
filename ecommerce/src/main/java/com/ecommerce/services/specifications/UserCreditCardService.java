package com.ecommerce.services.specifications;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardService {

    UserCreditCard findById(Long id);
    void save(UserCreditCard userCreditCard, Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(Long idUser, Long id);
    List<UserCreditCard> findUserCreditCards(Long id);
}
