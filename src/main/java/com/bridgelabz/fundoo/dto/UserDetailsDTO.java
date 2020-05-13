package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

	@Pattern(regexp = "^[a-zA-Z]{3,15}$", message = "Enter valid first name")
    public String firstName;
    
    @Pattern(regexp = "^[a-zA-Z]{3,15}$", message = "Enter valid last name")
    public String lastName;
    
    @Email(regexp = "^[a-zA-Z0-9]+[._+-]?[a-zA-Z0-9]*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,3}$", message = "Enter valid email")
    public String email;
    
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")
    public String password;
    
    @Range(min = 1, max = 150, message = "Enter valid age")
    public int age;
    
    
}
