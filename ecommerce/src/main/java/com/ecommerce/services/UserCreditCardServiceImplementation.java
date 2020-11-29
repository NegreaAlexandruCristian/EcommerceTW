package com.ecommerce.services;

import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.CrediCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCreditCardServiceImplementation implements UserCreditCardService{

    private final CrediCardRepository crediCardRepository;

    @Autowired
    public UserCreditCardServiceImplementation(CrediCardRepository crediCardRepository){
        this.crediCardRepository = crediCardRepository;
    }


    @Override
    public UserCreditCard findById(Long id) {
        return crediCardRepository.findById(id);
    }

    @Override
    public UserCreditCard save(UserCreditCard userCreditCard) {
        return crediCardRepository.save(userCreditCard);
    }

    @Override
    public boolean existsById(Long id) {
        return crediCardRepository.existsById(id);
    }

    @Override
    public List<UserCreditCard> findAll() {
        return crediCardRepository.findAll();
    }

    @Override
    public int count() {
        return crediCardRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        crediCardRepository.deleteById(id);
    }

    @Override
    public void delete(UserCreditCard userCreditCard) {
        crediCardRepository.delete(userCreditCard);
    }
}
