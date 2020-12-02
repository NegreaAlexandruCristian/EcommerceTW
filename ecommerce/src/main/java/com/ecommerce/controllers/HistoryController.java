package com.ecommerce.controllers;

import com.ecommerce.models.CartItems;
import com.ecommerce.models.HistoryItems;
import com.ecommerce.services.specifications.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO We should see the products, not their id's.
@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;


    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    //ok
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveItemInHistory(@RequestBody CartItems cartItems) {
        historyService.addProductToHistory(cartItems);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //ok
    @GetMapping("/{userId}")
    public ResponseEntity<List<HistoryItems>> getAllHistoryItems(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(historyService.findHistoryItemsByUserId(userId), HttpStatus.FOUND);
    }

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<HistoryItems> getProductById(@PathVariable("userId") Long userId,
                                                       @PathVariable("productId") Long productId) {
        return new ResponseEntity<>(historyService.findById(userId, productId), HttpStatus.FOUND);
    }

    //ok
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserHistory(@PathVariable("userId") Long userId) {
        historyService.deleteHistoryForUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<HttpStatus> deleteHistoryItem(@PathVariable("userId") Long userId,
                                                        @PathVariable("productId") Long productId) {
        historyService.deleteHistoryItem(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
