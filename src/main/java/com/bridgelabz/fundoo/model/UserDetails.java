package com.bridgelabz.fundoo.model;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int userId;
    public String firstName;
    public String lastName;
    public String password;
    public String email;
    public int age;
    public String mobileNumber;

    public UserDetails(UserDetailsDTO userDetailsDTO) {
        this.firstName = userDetailsDTO.firstName;
        this.lastName = userDetailsDTO.lastName;
        this.email = userDetailsDTO.email;
        this.password = userDetailsDTO.password;
        this.age = userDetailsDTO.age;
        this.mobileNumber = userDetailsDTO.mobileNumber;
    }
}
