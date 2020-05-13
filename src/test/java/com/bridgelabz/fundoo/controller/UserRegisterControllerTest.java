package com.bridgelabz.fundoo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import com.bridgelabz.fundoo.exception.FundooException;
import com.bridgelabz.fundoo.model.UserDetails;
import com.bridgelabz.fundoo.service.RegisterUser;
import com.google.gson.Gson;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	RegisterUser registerUser;
	
	@Autowired
	Gson gson;
	
	UserDetailsDTO userDetailsDTO;
	UserDetails userDetails;
	String userDetailsJson;
	
	@BeforeEach
	void setUp() {
		userDetailsDTO = new UserDetailsDTO("Clark", "Kent", "clarkkent@fundoo.com","Martha@123",31);
		userDetails = new UserDetails(userDetailsDTO);
		userDetailsJson = gson.toJson(userDetails);
	}
	
	@Test
	void givenRequest_whenGetResponse_shouldReturnStatusOk() throws Exception {
		when(registerUser.addUser(any())).thenReturn("UserRegistered Successful");
		MvcResult mvcResult = this.mockMvc.perform(post("/user/register").content(userDetailsJson)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		Assert.assertEquals(200, mvcResult.getResponse().getStatus());
		Assert.assertEquals("UserRegistered Successful", mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	void givenRequest_whenInvalidMediaType_shouldReturnStatus415() throws Exception {		
		MvcResult mvcResult = this.mockMvc.perform(post("/user/register").content(userDetailsJson)
				.contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
		Assert.assertEquals(415, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void givenRequest_whenInvalidURL_shouldReturnStatus404() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(post("/user/registeration").content(userDetailsJson)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		Assert.assertEquals(404, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void givenRequest_whenNoBody_shouldReturnStatus400() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(post("/user/register")).andReturn();
		Assert.assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void givenRequest_whenUserAlreadyRegistered_shouldThrowException() throws Exception {
		try {
			when(registerUser.addUser(any())).thenThrow(new FundooException("User Already Registered"));
			MvcResult mvcResult = this.mockMvc.perform(post("/user/register")).andReturn();
		} catch(FundooException e) {
			Assert.assertEquals("User Already Registered", e.getMessage());
		}
	}
}
