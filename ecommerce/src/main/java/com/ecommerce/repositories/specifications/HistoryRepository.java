package com.ecommerce.repositories.specifications;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.HistoryItems;

import java.util.List;

public interface HistoryRepository {
    List<HistoryItems> findHistoryItemsByUserId(Long userId);
    void addProductToHistory(CartItems cartItems);
    void deleteHistoryForUser(Long userId);
    void deleteHistoryItem(Long userId, Long productId);
    HistoryItems findById(Long userId, Long productId);
}
