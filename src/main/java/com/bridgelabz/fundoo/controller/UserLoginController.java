package com.bridgelabz.fundoo.controller;

import com.bridgelabz.fundoo.dto.UserLoginDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.service.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserLoginController {
	
	@Autowired
	UserLogin login;
	
	@GetMapping("/email/{email}")
	public boolean isEmailValid(@PathVariable String email) {
		return login.isEmailValid(email);		
	}
	
	@PostMapping("/login")
	public String getUserLoggedIn(@Valid @RequestBody UserLoginDTO userLogin, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			throw new FundooException("Enter valid input");
		return login.getUserLoggedIn(userLogin);
	}

}
