package com.michaeladrummonds.aguafina.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.michaeladrummonds.aguafina.models.Employee;

@Component
public interface EmployeeService {
    
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);
}
