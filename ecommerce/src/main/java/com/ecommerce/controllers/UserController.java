package com.ecommerce.controllers;

import com.ecommerce.models.CustomUser;
import com.ecommerce.models.Password;
import com.ecommerce.models.User;
import com.ecommerce.models.UserInformation;
import com.ecommerce.services.UserService;
import com.ecommerce.util.CustomPasswordEncoder;
import com.ecommerce.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserUtils userUtils;

    @Autowired
    public UserController(UserService userService, UserUtils userUtils) {
        this.userUtils = userUtils;
        this.userService = userService;
        User admin = new User();
        admin.setUsername("admin");
        CustomPasswordEncoder enc = new CustomPasswordEncoder();
        Password password = new Password();
        password.setPassword(enc.encode("admin123"));
        UserInformation userInformation = new UserInformation();
        userInformation.setPhone("0770122133");
        userInformation.setEmail("admin@gmail.com");
        userInformation.setFirstName("admin");
        userInformation.setLastName("admin");
        admin.setUserInformation(userInformation);
        admin.setPassword(password);
        admin.setRole("ADMIN");
        this.userService.save(admin);
    }

    private static List<String> getAuthorityList(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public  static boolean hasAuthority(Authentication authentication, String authorityName) {
        return getAuthorityList(authentication).contains(authorityName);
    }

    //ok
    @GetMapping("/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    //ok
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody CustomUser customUser) {
        return new ResponseEntity<>(userService.save(userUtils.createCompleteUser(customUser)), HttpStatus.OK);
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

    //ok
    @PutMapping("/changePassword")
    public ResponseEntity<HttpStatus> updatePassword(@RequestBody Password password) {
        userService.updatePassword(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ok
    @PutMapping("/changeInfo")
    public ResponseEntity<HttpStatus> updateInfo(@RequestBody UserInformation userInformation){
        userService.updateUserInformation(userInformation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO History, reviews, cart, creditcard later
}
