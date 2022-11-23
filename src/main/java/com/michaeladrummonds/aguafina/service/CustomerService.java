package com.michaeladrummonds.aguafina.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.michaeladrummonds.aguafina.models.Customer;

@Component
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    Customer getCustomerByEmail(String email);

    Customer updateCustomer(Customer customer);

    List<Customer> getCustomerByEmailContaining(String email);

    Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
