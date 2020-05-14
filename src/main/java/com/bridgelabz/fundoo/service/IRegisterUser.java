package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.model.UserDetails;

public interface IRegisterUser {
	public String addUser(UserDetailsDTO userRegisterDTO);
}
