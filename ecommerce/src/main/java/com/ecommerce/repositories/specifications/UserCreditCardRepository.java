package com.ecommerce.repositories.specifications;

import com.ecommerce.models.UserCreditCard;

public interface UserCreditCardRepository extends RepositoryManager<UserCreditCard, Long>{

    UserCreditCard findUsersCreditCard(Long idUser, Long id);
    void save(UserCreditCard creditCard, Long id);
    void delete(Long idUser, Long id);

    @Override
    default <S extends UserCreditCard> S save(S entity) {
        return null;
    }

    @Override
    default void delete(UserCreditCard entity) {

    }
}
