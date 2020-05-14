package com.bridgelabz.fundoo.repository;

import com.bridgelabz.fundoo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{
	Optional<UserDetails> findByEmail(@Email(message = "Enter valid email") String emailID);
}
