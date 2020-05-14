package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserLoginDTO;

public interface IUserLogin {
	public boolean isEmailValid(String email);
	public String getUserLoggedIn(UserLoginDTO userLogin);
}
