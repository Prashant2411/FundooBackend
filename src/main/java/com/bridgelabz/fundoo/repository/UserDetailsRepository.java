package com.bridgelabz.fundoo.repository;

import com.bridgelabz.fundoo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{
	Optional<UserDetails> findByEmail(@Email(message = "Enter valid email") String emailID);

	@Query(value = "Select email from user_details where mobile_number = :mobileNumber", nativeQuery = true)
	List<String> findEmailByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
