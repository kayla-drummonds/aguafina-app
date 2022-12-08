package com.michaeladrummonds.aguafina.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.michaeladrummonds.aguafina.validation.EmailUnique;
import com.michaeladrummonds.aguafina.validation.FieldMatch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match.")
})
public class UserRegistrationDto {

    @NotEmpty(message = "Email is required.")
    @Length(min = 5, max = 256, message = "Email must be less than 256 characters.")
    @EmailUnique(message = "User already exists with this email.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special characters")
    @Length(min = 8, max = 25, message = "Password must be between 8 to 25 characters.")
    private String password;

    @NotEmpty(message = "Confirm password is required.")
    private String confirmPassword;

    @NotEmpty(message = "First name is required.")
    @Length(min = 2, max = 45, message = "First name must be between 2 to 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(min = 2, max = 45, message = "Last name must be between 2 to 45 characters.")
    private String lastName;
}
