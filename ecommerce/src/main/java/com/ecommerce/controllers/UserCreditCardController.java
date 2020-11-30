package com.ecommerce.controllers;

import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.services.UserCreditCardService;
import com.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditcard")
public class UserCreditCardController {

    private final UserService userService;
    private final UserCreditCardService userCreditCardService;

    @Autowired
    public UserCreditCardController(UserService userService, UserCreditCardService userCreditCardService){
        this.userService = userService;
        this.userCreditCardService = userCreditCardService;
    }

    //ok
    @PostMapping("/save/{id}")
    public ResponseEntity<UserCreditCard> saveCreditCard(@RequestBody UserCreditCard userCreditCard,
                                                         @PathVariable("id") Long id){
        userCreditCard.setId(null);
        User user = userService.findById(id);
        user.addCreditCard(userCreditCard);
        userService.save(user);

        return new ResponseEntity<>(userCreditCard, HttpStatus.OK);
    }

    //ok
    @GetMapping("/{idUser}/{id}")
    public ResponseEntity<UserCreditCard> getCreditCard(@PathVariable("idUser") Long idUser,
                                                         @PathVariable("id") Long id){

        return new ResponseEntity<UserCreditCard>(userCreditCardService.findUsersCreditCard(idUser,id), HttpStatus.OK);
    }

    //ok
    @GetMapping("/{idUser}")
    public ResponseEntity<List<UserCreditCard>> getUserCreditCards(@PathVariable("idUser") Long idUser){

        return new ResponseEntity<List<UserCreditCard>>(userService.findById(idUser).getUserCreditCards(), HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/delete/{idUser}/{id}")
    public ResponseEntity<HttpStatus> deleteCreditCard(@PathVariable("idUser") Long idUser,
                                                       @PathVariable("id") int id){

        User user = userService.findById(idUser);
        List<UserCreditCard> list = user.getUserCreditCards();
        UserCreditCard userCreditCard = list.get(id-1);
        userCreditCardService.delete(userCreditCard);

        list.remove(id-1);
        user.setUserCreditCards(list);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
