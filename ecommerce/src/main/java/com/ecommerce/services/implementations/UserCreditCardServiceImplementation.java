package com.ecommerce.services.implementations;

import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import com.ecommerce.services.specifications.UserCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserCreditCardServiceImplementation implements UserCreditCardService {

    private final UserCreditCardRepository crediCardRepository;

    @Autowired
    public UserCreditCardServiceImplementation(UserCreditCardRepository crediCardRepository){
        this.crediCardRepository = crediCardRepository;
    }


    @Override
    public UserCreditCard findById(Long id) {
        return crediCardRepository.findById(id);
    }

    @Override
    public void save(UserCreditCard userCreditCard, Long id) {
        crediCardRepository.save(userCreditCard, id);
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
    public void delete(Long idUser, Long id) {
        crediCardRepository.delete(idUser, id);
    }

    @Override
    public UserCreditCard findUsersCreditCard(Long idUser, Long id) {
        return crediCardRepository.findUsersCreditCard(idUser, id);
    }
}