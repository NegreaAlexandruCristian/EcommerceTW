package com.ecommerce.services;

import com.ecommerce.models.UserInformation;
import com.ecommerce.repositories.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationServiceImplementation implements UserInformationService{

    private final UserInformationRepository userInformationRepository;

    @Autowired
    public UserInformationServiceImplementation(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    @Override
    public UserInformation findById(Long id) {
        return this.userInformationRepository.findById(id);
    }

    @Override
    public UserInformation save(UserInformation userInformation) {
        return this.userInformationRepository.save(userInformation);
    }
}
