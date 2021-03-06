package com.ecommerce.services.specifications;

import com.ecommerce.models.UserAddress;

import java.util.List;

public interface UserAddressService{


    List<UserAddress> findUserAddresses(Long idUser);
    UserAddress findById(Long addressId);
    UserAddress save(UserAddress userAddress, Long userId);
    void deleteById(Long userId ,Long id);
    void deleteAddressesForUser(Long idUser);
}
