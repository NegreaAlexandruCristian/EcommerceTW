package com.ecommerce.services.implementations;

import com.ecommerce.models.CartItems;
import com.ecommerce.repositories.implementations.CartRepositoryImplementation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConsultCartService {

    private final CartRepositoryImplementation cartRepositoryImplementation;

    public ConsultCartService(CartRepositoryImplementation cartRepositoryImplementation) {
        this.cartRepositoryImplementation = cartRepositoryImplementation;
    }

    public List<CartItems> consult(Long userId) {
        return cartRepositoryImplementation.findCartItemsByUserId(userId);
    }
}
