package com.michaeladrummonds.aguafina.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    @NotEmpty(message = "Customer is required.")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    @NotEmpty(message = "Employee is required.")
    private Employee employee;

    @Column
    @NotNull
    @NotEmpty(message = "Product is required.")
    private String product;

    @Column
    @NotEmpty(message = "Quantity is required.")
    @Positive(message = "Quantity must be positive.")
    @Max(value = 10, message = "Quantity must be less than or equal to 10.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    private Integer quantity;

    @Column
    private Double total;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
