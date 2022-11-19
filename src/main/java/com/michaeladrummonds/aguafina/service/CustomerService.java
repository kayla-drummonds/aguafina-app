package com.michaeladrummonds.aguafina.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.michaeladrummonds.aguafina.models.Customer;

@Component
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    List<Customer> findByLastNameContaining(String lastName);
}
