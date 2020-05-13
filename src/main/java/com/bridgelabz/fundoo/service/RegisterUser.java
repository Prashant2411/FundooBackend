package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import com.bridgelabz.fundoo.repository.IUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUser implements IRegister {

    @Autowired
    IUserDetails userDetailsRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public String addUser(UserDetailsDTO userRegisterDTO) {
        userRegisterDTO.password = encoder.encode(userRegisterDTO.password);
        UserDetails user = new UserDetails(userRegisterDTO);
        Optional<UserDetails> isEmailRegistered = userDetailsRepo.findByEmail(userRegisterDTO.email);
        if(isEmailRegistered.isPresent())
            throw new FundooException("User already registered with this email id");
        userDetailsRepo.save(user);
        return "User Registered Successful";
    }
}
