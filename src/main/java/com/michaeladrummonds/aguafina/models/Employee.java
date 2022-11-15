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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    @EqualsAndHashCode.Include
    private String firstName;

    @Column
    @EqualsAndHashCode.Include
    private String lastName;

    @Column
    @EqualsAndHashCode.Include
    private String email;

    @Column
    @EqualsAndHashCode.Include
    private String address;

    @Column
    @EqualsAndHashCode.Include
    private String city;

    @Column
    @EqualsAndHashCode.Include
    private String state;

    @Column
    @EqualsAndHashCode.Include
    private String zipCode;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "employee", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;
}
