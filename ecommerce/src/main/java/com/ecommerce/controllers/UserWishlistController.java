package com.ecommerce.controllers;

import com.ecommerce.models.UserWishlist;
import com.ecommerce.services.specifications.UserWishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class UserWishlistController {
    private final UserWishlistService userWishlistService;

    public UserWishlistController(UserWishlistService userWishlistService) {
        this.userWishlistService = userWishlistService;
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<HttpStatus> addProductToWishlist(@PathVariable("userId") Long userId,
                                                           @PathVariable("productId") Long productId) {
        userWishlistService.addProductToWishlist(userId, productId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserWishlist>> retrieveUserWishlist(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userWishlistService.findWishlistItemsByUserId(userId), HttpStatus.FOUND);
    }

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<UserWishlist> retrieveUserWishlistItem(@PathVariable("userId") Long userId,
                                                                 @PathVariable("productId") Long productId) {
        return new ResponseEntity<>(userWishlistService.findProductById(userId, productId), HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUserWishlist(@PathVariable("userId") Long userId) {
        userWishlistService.deleteWishlistItems(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<HttpStatus> deleteUserWishlistItem(@PathVariable("userId") Long userId,
                                                             @PathVariable("productId") Long productId) {
        userWishlistService.deleteWishlistItem(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
