package com.ecommerce.controllers;

import com.ecommerce.models.UserCreditCard;
import com.ecommerce.services.specifications.UserCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditcard")
public class UserCreditCardController {

    private final UserCreditCardService userCreditCardService;

    @Autowired
    public UserCreditCardController(UserCreditCardService userCreditCardService){
        this.userCreditCardService = userCreditCardService;
    }

    //ok
    @PostMapping("/save/{id}")
    public ResponseEntity<HttpStatus> saveCreditCard(@RequestBody UserCreditCard userCreditCard,
                                                         @PathVariable("id") Long id){
        userCreditCardService.save(userCreditCard, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @GetMapping("/{idUser}")
    public ResponseEntity<List<UserCreditCard>> getUserCreditCards(@PathVariable("idUser") Long idUser){

        return new ResponseEntity<>(userCreditCardService.findUserCreditCards(idUser), HttpStatus.OK);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<UserCreditCard> getCurrentCreditCard(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userCreditCardService.findById(id), HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/delete/{idUser}/{id}")
    public ResponseEntity<HttpStatus> deleteCreditCard(@PathVariable("idUser") Long idUser,
                                                       @PathVariable("id") Long id){

        userCreditCardService.delete(idUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
