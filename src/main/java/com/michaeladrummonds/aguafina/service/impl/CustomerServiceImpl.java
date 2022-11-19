package com.michaeladrummonds.aguafina.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.repository.CustomerRepository;
import com.michaeladrummonds.aguafina.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findByLastNameContaining(String lastName) {
        return customerRepository.findByLastNameContaining(lastName);
    }

}
