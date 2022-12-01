package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees")
    public String listAllEmployees(Model model, Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }
}
