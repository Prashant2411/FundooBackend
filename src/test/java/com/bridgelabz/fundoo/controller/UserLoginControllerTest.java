package com.bridgelabz.fundoo.controller;

import com.bridgelabz.fundoo.dto.UserLoginDTO;
import com.bridgelabz.fundoo.service.UserLogin;
import com.bridgelabz.fundoo.util.JWTTokenUtil;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserLogin userLogin;

    @Autowired
    Gson gson;

    @MockBean
    JWTTokenUtil jwtTokenUtil;

    UserLoginDTO userLoginDTO;
    String userLoginJson;

    @BeforeEach
    void setUp() {
        userLoginDTO = new UserLoginDTO("prashantbedi@fundoo.com","Password@123");
        userLoginJson = gson.toJson(userLoginDTO);
    }

    //EmailValid

    @Test
    void givenEmailVerifyRequest_whenGetResponse_shouldReturnStatusOk() throws Exception {
        when(userLogin.isEmailValid(any())).thenReturn(false);
        MvcResult mvcResult = this.mockMvc.perform(get("/user/email/prashantbedi@fundoo.com")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals("false", mvcResult.getResponse().getContentAsString());
    }

    //User Login

    @Test
    void givenRequest_whenGetResponse_shouldReturnStatusOk() throws Exception {
        when(userLogin.getUserLoggedIn(any())).thenReturn("asdi802owdqa.asda21.fvkpn1");
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login").content(userLoginJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals("asdi802owdqa.asda21.fvkpn1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void givenRequest_whenInvalidMediaType_shouldReturnStatus415() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login").content(userLoginJson)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        Assert.assertEquals(415, mvcResult.getResponse().getStatus());
    }

    @Test
    void givenRequest_whenInvalidURL_shouldReturnStatus404() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/user/loogin").content(userLoginJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    void givenRequest_whenNoBody_shouldReturnStatus400() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login")).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void givenRequest_whenInvalidData_shouldThrowException() throws Exception {
        userLoginDTO.email = "prashantbedi";
        userLoginJson = gson.toJson(userLoginDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/login").content(userLoginJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals("Enter valid input", mvcResult.getResponse().getContentAsString());
    }

    //Forgot Email

    @Test
    void givenMobileNumber_whenForgotEmail_shouldReturnEmail() throws Exception {
        List<String> email = new ArrayList<String>();
        email.add("clarkkent@fundoo.com");
        email.add("brucewayne@fundoo.com");
        when(userLogin.forgetEmail(any())).thenReturn(email);
        MvcResult mvcResult = this.mockMvc.perform(get("/user/forget-email/9090909090")).andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(email.get(0)));
    }
}
