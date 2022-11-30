package com.michaeladrummonds.aguafina.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    @NotEmpty(message = "Email is required.")
    @Length(max = 256, message = "Email must be less than 256 characters.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special characters")
    @Length(min = 8, message = "Password must be at least 8 characters.")
    @Length(max = 25, message = "Password must be shorter than 25 characters.")
    private String password;

    @NotEmpty(message = "Confirm password is required.")
    private String confirmPassword;

    @NotEmpty(message = "First name is required.")
    @Length(max = 45, message = "First name must be less than 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(max = 45, message = "Last name must be less than 45 characters.")
    private String lastName;
}
