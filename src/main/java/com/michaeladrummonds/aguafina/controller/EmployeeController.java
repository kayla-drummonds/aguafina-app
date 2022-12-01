package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.michaeladrummonds.aguafina.config.AuthenticatedUserService;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.User;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AuthenticatedUserService authService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees")
    public ModelAndView listAllEmployees() {
        ModelAndView mav = new ModelAndView("employees");

        User user = authService.getCurrentUser();

        Employee employee = employeeService.getEmployeeByEmail(user.getEmail());
        List<Employee> employees = employeeService.getAllEmployees();

        mav.addObject("employee", employee);
        mav.addObject("employees", employees);

        return mav;
    }

}
