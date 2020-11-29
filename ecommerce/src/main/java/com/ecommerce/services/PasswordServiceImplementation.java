package com.ecommerce.services;

import com.ecommerce.models.Password;
import com.ecommerce.repositories.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImplementation implements PasswordService{

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordServiceImplementation(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }


    @Override
    public Password findById(Long id) {
        return passwordRepository.passwordById(id);
    }

    @Override
    public void save(Password password) {
        passwordRepository.savePassword(password);
    }
}
