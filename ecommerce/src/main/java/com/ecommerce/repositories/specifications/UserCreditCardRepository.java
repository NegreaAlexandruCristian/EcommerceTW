package com.ecommerce.repositories.specifications;

import com.ecommerce.models.UserCreditCard;

import java.util.List;

public interface UserCreditCardRepository extends RepositoryManager<UserCreditCard, Long>{

    void save(UserCreditCard creditCard, Long id);
    void delete(Long idUser, Long id);
    List<UserCreditCard> findUserCreditCards(Long userId);

    @Override
    default int count(){
        return 0;
    }

    @Override
    default List<UserCreditCard> findAll(){
        return null;
    };

    @Override
    default <S extends UserCreditCard> S save(S entity) {
        return null;
    }

    @Override
    default void delete(UserCreditCard entity) {

    }
}
