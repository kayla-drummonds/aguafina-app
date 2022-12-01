package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees")
    public ModelAndView listAllEmployees(Authentication authentication) {
        ModelAndView mav = new ModelAndView("employees");

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        List<Employee> employees = employeeService.getAllEmployees();

        mav.addObject("employee", employee);
        mav.addObject("employees", employees);

        return mav;
    }

}
