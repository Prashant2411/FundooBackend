package com.bridgelabz.fundoo.controller;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import com.bridgelabz.fundoo.service.RegisterUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    RegisterUser userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDetailsDTO userRegisterDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new FundooException("Enter valid input");
        return userService.addUser(userRegisterDTO);
    }
}
