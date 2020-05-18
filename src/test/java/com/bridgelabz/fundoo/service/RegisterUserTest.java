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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class RegisterUserTest {

    @Mock
    UserDetailsRepository userDetailsRepo;

    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    RegisterUser registerUser;

    UserDetailsDTO userDetailsDTO;
    UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetailsDTO = new UserDetailsDTO("Clark", "Kent", "clarkkent@fundoo.com", "Martha@123", 31, "7897897897");
        userDetails = new UserDetails(1,"Clark", "Kent", "clarkkent@fundoo.com", "Martha@123", 31, "7897897897");
    }

    //Register

    @Test
    void givenUserDetails_whenRegisterDetails_shouldReturnUserAddedSuccessfully() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        when(userDetailsRepo.save(any())).thenReturn(userDetails);
        when(encoder.encode(any())).thenReturn("3$5#enidn#einf1241");
        String message = registerUser.addUser(userDetailsDTO);
        Assert.assertEquals("User Registered Successfully", message);
    }

    @Test
    void givenUserDetails_whenEmailAlreadyUsed_thenReturnException() {
        try {
            when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.of(userDetails));
            when(encoder.encode(any())).thenReturn("3$5#enidn#einf1241");
            registerUser.addUser(userDetailsDTO);
        } catch (FundooException e) {
            Assert.assertEquals("User already registered with this email id",e.getMessage());
        }
    }

    //Email Availability

    @Test
    void givenEmailForAvailability_whenAvailable_thenReturnTrue() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        boolean emailAvailable = registerUser.isEmailAvailable("prashantbedi@gmail.com");
        Assert.assertTrue(emailAvailable);
    }

    @Test
    void givenEmailForAvailability_whenNotAvailable_thenReturnFalse() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.of(userDetails));
        boolean emailAvailable = registerUser.isEmailAvailable("prashantbedi@gmail.com");
        Assert.assertFalse(emailAvailable);
    }
}
