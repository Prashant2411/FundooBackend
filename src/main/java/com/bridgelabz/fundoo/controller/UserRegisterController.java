package com.bridgelabz.fundoo.controller;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.service.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRegisterController {

    @Autowired
    RegisterUser userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDetailsDTO userRegisterDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new FundooException("Enter valid input");
        return userService.addUser(userRegisterDTO);
    }

    @GetMapping("/email-availability/{email}")
    public boolean isEmailAvailable(@PathVariable String email) {
        return userService.isEmailAvailable(email);
    }
}
