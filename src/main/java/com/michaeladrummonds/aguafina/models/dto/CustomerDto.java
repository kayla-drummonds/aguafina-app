package com.michaeladrummonds.aguafina.models.dto;

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
public class CustomerDto {

    @NotEmpty(message = "First name is required.")
    @Length(min = 2, max = 45, message = "First name must be between 2 and 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(min = 2, max = 45, message = "Last name must be between 2 and 45 characters.")
    private String lastName;

    @NotEmpty(message = "Email is required.")
    @Length(max = 255, message = "Email must be less than 255 characters.")
    private String email;

    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone number must only contain numbers and dashes.")
    private String phone;
}
