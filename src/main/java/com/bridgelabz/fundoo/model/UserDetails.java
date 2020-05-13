package com.bridgelabz.fundoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgelabz.fundoo.dto.UserDetailsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
