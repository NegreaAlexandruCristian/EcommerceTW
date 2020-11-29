package com.ecommerce.controllers;

import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.services.UserCreditCardService;
import com.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcard")
public class UserCreditCardController {

    private final UserCreditCardService userCreditCardService;
    private final UserService userService;

    @Autowired
    public UserCreditCardController(UserCreditCardService userCreditCardService, UserService userService){
        this.userCreditCardService = userCreditCardService;
        this.userService = userService;
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<UserCreditCard> saveCreditCard(@RequestBody UserCreditCard userCreditCard,
                                                         @PathVariable("id") Long id){

        User user = userService.findById(id);;
        userCreditCard.setUser(user);
        UserCreditCard createdUserCreditCard = userCreditCardService.save(userCreditCard);

        return new ResponseEntity<>(createdUserCreditCard, HttpStatus.OK);
    }
}
