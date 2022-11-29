package com.michaeladrummonds.aguafina.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Customer> getAllCustomersPageable(Pageable pageable) {
        return customerRepository.findAll(pageable);
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
    public List<Customer> getCustomerByKeyword(String keyword) {
        return customerRepository.findCustomerByKeyword(keyword);
    }

    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
