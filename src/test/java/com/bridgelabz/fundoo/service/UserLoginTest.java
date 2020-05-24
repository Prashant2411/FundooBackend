package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserLoginDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import com.bridgelabz.fundoo.repository.UserDetailsRepository;
import com.bridgelabz.fundoo.util.JWTTokenUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserLoginTest {

    @Mock
    UserDetailsRepository userDetailsRepo;

    @Mock
    JWTTokenUtil jwtTokenUtil;

    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    UserLogin userLogin;

    UserLoginDTO userLoginDTO;
    UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userLoginDTO = new UserLoginDTO("clarkkent@fundoo.com", "Martha@123");
        userDetails = new UserDetails(1, "Clark", "Kent", "clarkkent@fundoo.com", "Martha@123", 31, "7897897897");
    }

    //Verify Email

    @Test
    void givenValidEmail_whenVerifyEmail_shouldReturnTrue() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.of(userDetails));
        Boolean isEmailValid = userLogin.isEmailValid("prashantbedi@fundoo.com");
        Assert.assertTrue(isEmailValid);
    }

    @Test
    void givenInvalidEmail_whenVerifyEmail_shouldReturnFalse() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        Boolean isEmailValid = userLogin.isEmailValid("prashantbedi@fundoo.com");
        Assert.assertFalse(isEmailValid);
    }

    //Login User

    @Test
    void givenUserLoginDetails_whenLoginUser_shouldReturnToken() {
        when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.of(userDetails));
        when(jwtTokenUtil.generateToken(any())).thenReturn("asdasd231");
        when(encoder.matches(any(), any())).thenReturn(true);
        String message = userLogin.getUserLoggedIn(userLoginDTO);
        Assert.assertEquals("asdasd231", message);
    }

    @Test
    void givenUserLoginDetails_whenEmailNotFound_thenReturnException() {
        try {
            when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
            userLogin.getUserLoggedIn(userLoginDTO);
        } catch (FundooException e) {
            Assert.assertEquals("No such account found", e.getMessage());
        }
    }

    @Test
    void givenUserLoginDetails_whenPasswordInvalid_thenThrowException() {
        try {
            when(userDetailsRepo.findByEmail(any())).thenReturn(Optional.of(userDetails));
            when(encoder.matches(any(), any())).thenReturn(false);
            String message = userLogin.getUserLoggedIn(userLoginDTO);
            Assert.assertEquals("asdasd231", message);
        } catch (FundooException e) {
            Assert.assertEquals("Enter valid password", e.getMessage());
        }
    }

    //Forgot Email


    @Test
    void givenMobileNumber_whenForgotEmail_shouldReturnEmail() {
        List<String> email = new ArrayList<String>();
        email.add("clarkkent@fundoo.com");
        email.add("brucewayne@fundoo.com");
        when(userDetailsRepo.findEmailByMobileNumber(any())).thenReturn(email);
        List<String> emailList = userLogin.forgetEmail("9090909090");
        Assert.assertEquals(email, emailList);
    }

    @Test
    void givenMobileNumber_whenMobileNumberNotFound_shouldReturnException() {
        try {
            when(userDetailsRepo.findEmailByMobileNumber(any())).thenReturn(new ArrayList<String>());
            List<String> emailList = userLogin.forgetEmail("9090909090");
        } catch(FundooException e) {
            Assert.assertEquals("No acoount linked with this email",e.getMessage());
        }
    }
}
