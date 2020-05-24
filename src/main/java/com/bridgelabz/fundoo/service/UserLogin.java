package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserLoginDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import com.bridgelabz.fundoo.repository.UserDetailsRepository;
import com.bridgelabz.fundoo.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLogin implements IUserLogin {

	@Autowired
	UserDetailsRepository userDetailsRepo;
	
	@Autowired
    BCryptPasswordEncoder encoder;
	
	@Autowired
	JWTTokenUtil tokenGenerator;
	
	@Override
	public boolean isEmailValid(String email) {
		Optional<UserDetails> isEmailPresent = userDetailsRepo.findByEmail(email);
		return !isEmailPresent.isEmpty();
	}
	
	@Override
	public String getUserLoggedIn(UserLoginDTO userLogin) {
		Optional<UserDetails> isEmailPresent = userDetailsRepo.findByEmail(userLogin.email);
		if (isEmailPresent.isEmpty())
            throw new FundooException("No such account found");
        if (encoder.matches(userLogin.password,isEmailPresent.get().password))
            return tokenGenerator.generateToken(userLogin);
        throw new FundooException("Enter valid password");
	}

	public List forgetEmail(String mobileNumber) {
		List<String> byMobileNumber = userDetailsRepo.findEmailByMobileNumber(mobileNumber);
		if(byMobileNumber.size() == 0 )
			throw new FundooException("No acoount linked with this email");
		return byMobileNumber;
	}
}
