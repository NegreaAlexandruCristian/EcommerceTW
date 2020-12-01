package com.ecommerce.controllers;

import com.ecommerce.models.UserCreditCard;
import com.ecommerce.services.specifications.UserCreditCardService;
import com.ecommerce.services.specifications.UserService;
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
    public ResponseEntity<HttpStatus> saveCreditCard(@RequestBody UserCreditCard userCreditCard,
                                                         @PathVariable("id") Long id){
        userCreditCardService.save(userCreditCard, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @GetMapping("/{idUser}/{id}")
    public ResponseEntity<UserCreditCard> getCreditCard(@PathVariable("idUser") Long idUser,
                                                         @PathVariable("id") Long id){

        if(userCreditCardService.existsById(id)){

            return new ResponseEntity<>(userCreditCardService.findUsersCreditCard(idUser,id), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //ok
    @GetMapping("/{idUser}")
    public ResponseEntity<List<UserCreditCard>> getUserCreditCards(@PathVariable("idUser") Long idUser){

        return new ResponseEntity<>(userService.findById(idUser).getUserCreditCards(), HttpStatus.OK);
    }

    //ok
    @DeleteMapping("/delete/{idUser}/{id}")
    public ResponseEntity<HttpStatus> deleteCreditCard(@PathVariable("idUser") Long idUser,
                                                       @PathVariable("id") Long id){

        userCreditCardService.delete(idUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
