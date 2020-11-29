package com.ecommerce.controllers;

import com.ecommerce.models.*;
import com.ecommerce.services.PasswordService;
import com.ecommerce.services.UserInformationService;
import com.ecommerce.services.UserService;
import com.ecommerce.util.ConversionUserPasswordUtility;
import com.ecommerce.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;
    private final UserInformationService userInformationService;

    @Autowired
    public UserController(@Qualifier("userServiceImplementation") UserService userService,
                          @Qualifier("passwordServiceImplementation") PasswordService passwordService,
                          @Qualifier("userInformationServiceImplementation") UserInformationService userInformationService){

        this.userService = userService;
        this.passwordService = passwordService;
        this.userInformationService = userInformationService;
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){

        User user = userService.findById(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody CustomUser customUser){

        User user = ConversionUserPasswordUtility.CustomUserToUser(customUser);

        String rawPassword = customUser.getPassword();
        String encoded = new CustomPasswordEncoder().encode(rawPassword);
        Password password = ConversionUserPasswordUtility.CustomUserToPassword(customUser,user);
        password.setPassword(encoded);

        UserInformation userInformation = ConversionUserPasswordUtility.CustomUserToUserInformation(customUser,user);

        user.setPassword(password);
        //user.setUserInformation(userInformation);
        User createdUser = userService.save(user);

        System.out.println(createdUser);
        System.out.println(userInformation);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
}
