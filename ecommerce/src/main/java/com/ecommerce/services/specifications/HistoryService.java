package com.ecommerce.services.specifications;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.HistoryItems;

import java.util.List;

public interface HistoryService {
    List<HistoryItems> findHistoryItemsByUserId(Long userId);
    void addProductToHistory(CartItems cartItems);
    void deleteHistoryForUser(Long userId);
    void deleteHistoryItem(Long userId, Long productId);
}
