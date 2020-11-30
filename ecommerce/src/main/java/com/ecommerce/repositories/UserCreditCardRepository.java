package com.ecommerce.repositories;

import com.ecommerce.models.UserCreditCard;

public interface UserCreditCardRepository extends RepositoryManager<UserCreditCard, Long> {

    UserCreditCard findUsersCreditCard(Long idUser, Long id);
}
