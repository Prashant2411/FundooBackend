package com.bridgelabz.fundoo.repository;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.model.UserDetails;

public interface IUserDetails extends JpaRepository<UserDetails, Integer>{
	Optional<UserDetails> findByEmail(@Email(message = "Enter valid email") String emailID);
}
