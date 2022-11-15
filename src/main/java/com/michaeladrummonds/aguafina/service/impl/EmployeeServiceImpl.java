package com.michaeladrummonds.aguafina.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.repository.EmployeeRepository;
import com.michaeladrummonds.aguafina.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

}
