package com.ecommerce.controllers;

import com.ecommerce.models.*;
import com.ecommerce.services.UserService;
import com.ecommerce.util.ConversionUserPasswordUtility;
import com.ecommerce.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }
    //ok
    @GetMapping("/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //ok
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody CustomUser customUser) {
        User user = ConversionUserPasswordUtility.CustomUserToUser(customUser);
        String rawPassword = customUser.getPassword();
        String encoded = new CustomPasswordEncoder().encode(rawPassword);
        Password password = ConversionUserPasswordUtility.CustomUserToPassword(customUser, user);
        password.setPassword(encoded);
        UserInformation userInformation = ConversionUserPasswordUtility.CustomUserToUserInformation(customUser, user);
        user.setPassword(password);
        user.setUserInformation(userInformation);

        User createdUser = userService.save(user);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
    //ok
    @GetMapping("/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable("id") Long id) {
        return new ResponseEntity<Boolean>(userService.existsById(id),HttpStatus.ACCEPTED);
    }
    //ok
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
    //ok
    @GetMapping("/count")
    public ResponseEntity<Integer> countUsers(){
        return new ResponseEntity<>(userService.count(), HttpStatus.OK);
    }
    //ok
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id")Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //ok
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody User user){
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<HttpStatus> updatePassword(@RequestBody Password password) {
        userService.updatePassword(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
