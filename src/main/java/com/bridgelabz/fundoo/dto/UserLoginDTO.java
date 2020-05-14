package com.bridgelabz.fundoo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @Email(regexp = "^[a-zA-Z0-9]+[._+-]?[a-zA-Z0-9]*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,3}$", message = "Enter valid email")
    public String email;
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")
    public String password;
}
