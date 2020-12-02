package com.ecommerce.services.implementations;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.HistoryItems;
import com.ecommerce.repositories.specifications.HistoryRepository;
import com.ecommerce.services.specifications.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistoryServiceImplementation implements HistoryService {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImplementation(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    @Override
    public List<HistoryItems> findHistoryItemsByUserId(Long userId) {
        return historyRepository.findHistoryItemsByUserId(userId);
    }

    @Override
    public void addProductToHistory(CartItems cartItems) {
        historyRepository.addProductToHistory(cartItems);
    }

    @Override
    public void deleteHistoryForUser(Long userId) {
        historyRepository.deleteHistoryForUser(userId);
    }

    @Override
    public void deleteHistoryItem(Long userId, Long productId) {
        historyRepository.deleteHistoryItem(userId, productId);
    }
}
