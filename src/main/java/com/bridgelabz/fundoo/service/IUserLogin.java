package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserLoginDTO;

import java.util.List;

public interface IUserLogin {
	boolean isEmailValid(String email);
	String getUserLoggedIn(UserLoginDTO userLogin);
}
