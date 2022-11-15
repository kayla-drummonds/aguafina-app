package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;
    
    @GetMapping("/employees")
    public String listAllEmployees(Model model, @ModelAttribute("employee") Employee employee) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
