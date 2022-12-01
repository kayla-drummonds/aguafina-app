package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.service.impl.CustomerServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    // displays all customers
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping({ "/customers", "/customers/page/{pageNo}" })
    public String listCustomers(Model model,
            @RequestParam(value = "keyword", required = false) String keyword, Authentication authentication) {

        String username = authentication.getName();

        Employee employee = employeeService.getEmployeeByEmail(username);

        model.addAttribute("employee", employee);
        if (employee != null) {
            if (keyword != null) {
                List<Customer> customers = customerService.getCustomerByKeyword(keyword);
                model.addAttribute("customers", customers);
                model.addAttribute("keyword", keyword);
            } else {
                List<Customer> customers = customerService.getAllCustomers();
                model.addAttribute("customers", customers);
                model.addAttribute("keyword", keyword);
            }
        }

        return "customers";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE','CUSTOMER')")
    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model, Authentication authentication) {
        model.addAttribute("customer", customerService.getCustomerById(id));

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        return "edit_customer";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE','CUSTOMER')")
    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Integer id, @ModelAttribute("customer") Customer customer, Model model) {
        Customer existingCustomer = customerService.getCustomerById(id);
        existingCustomer.setId(customer.getId());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setState(customer.getState());
        existingCustomer.setZipCode(customer.getZipCode());

        customerService.updateCustomer(existingCustomer);
        return "redirect:/home";
    }
}
