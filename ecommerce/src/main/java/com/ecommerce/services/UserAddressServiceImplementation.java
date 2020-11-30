package com.ecommerce.services;

import com.ecommerce.models.UserAddress;
import com.ecommerce.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserAddressServiceImplementation implements UserAddressService{

    private UserAddressRepository userAddressRepository;

    @Autowired
    public UserAddressServiceImplementation(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public UserAddress findUsersAddresses(Long idUser, Long id) {
        return userAddressRepository.findUsersAddresses(idUser, id);
    }

    @Override
    public void save(UserAddress userAddress, Long id) {
        userAddressRepository.save(userAddress, id);
    }

    @Override
    public UserAddress findById(Long id) {
        return userAddressRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userAddressRepository.existsById(id);
    }

    @Override
    public List<UserAddress> findAll() {
        return userAddressRepository.findAll();
    }

    @Override
    public int count() {
        return userAddressRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        userAddressRepository.deleteById(id);
    }

    @Override
    public void delete(Long idUser, Long id) {
        userAddressRepository.delete(idUser, id);
    }
}
