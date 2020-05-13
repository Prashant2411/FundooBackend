package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.swing.JOptionPane;

import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.repository.UserDetailsRepository;

import org.junit.Assert;

@SpringBootTest
public class RegisterUserTest {

    @Mock
    UserDetailsRepository userDetailsRepo;

    @InjectMocks
    RegisterUser registerUser;

    UserDetailsDTO userDetailsDTO;
    UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetailsDTO = new UserDetailsDTO("Clark", "Kent", "clarkkent@fundoo.com", "Martha@123", 31, "7897897897");
        userDetails = new UserDetails(userDetailsDTO);
    }

    @Test
    void givenUserDetails_whenRegisterDetails_shouldReturnUserAddedSuccessfully() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        when(userDetailsRepo.save(any())).thenReturn(userDetails);
        String message = registerUser.addUser(userDetailsDTO);
        Assert.assertEquals("User Registered Successfully", message);
    }

    @Test
    void givenUserDetails_whenEmailAlreadyUsed_thenReturnException() {
        try {
            when(userDetailsRepo.findByEmail(any())).thenThrow(new FundooException("User already registered with this email id"));
        } catch (FundooException e) {
            Assert.assertEquals("User already registered with this email id",e.getMessage());
        }
    }
}
