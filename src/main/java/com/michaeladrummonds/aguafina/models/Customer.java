package com.michaeladrummonds.aguafina.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotEmpty(message = "First name is required.")
    @Length(min = 2, max = 45, message = "First name must be between 2 and 45 characters.")
    private String firstName;

    @Column
    @NotEmpty(message = "Last name is required.")
    @Length(min = 2, max = 45, message = "Last name must be between 2 and 45 characters.")
    private String lastName;

    @Column
    @NotEmpty(message = "Email is required.")
    @Length(max = 255, message = "Email must be less than 255 characters.")
    private String email;

    @Column
    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone number must only contain numbers and dashes.")
    private String phone;

    @Column
    @NotEmpty(message = "Street address is required.")
    @Length(max = 45, message = "Street address must be less than 45 characters.")
    private String address;

    @Column
    @NotEmpty(message = "City is required.")
    @Length(max = 45, message = "City must be less than 45 characters.")
    private String city;

    @Column
    @NotEmpty(message = "State is required.")
    @Length(max = 45, message = "State must be less than 45 characters.")
    private String state;

    @Column
    @NotEmpty(message = "Zip code is required.")
    @Pattern(regexp = "^\\d{5}$", message = "Zip code must be 5 digits long and only contain numbers.")
    private String zipCode;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

}
