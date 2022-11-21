package com.michaeladrummonds.aguafina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaeladrummonds.aguafina.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findCustomerByEmailContaining(String email);

    public Customer findByEmail(String email);
}
