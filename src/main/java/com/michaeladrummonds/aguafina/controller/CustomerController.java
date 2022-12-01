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
import org.springframework.web.servlet.ModelAndView;

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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping({ "/customers", "/customers/page/{pageNo}" })
    public ModelAndView listCustomers(@RequestParam(value = "keyword", required = false) String keyword,
            Authentication authentication) {
        ModelAndView mav = new ModelAndView("customers");

        String username = authentication.getName();

        Employee employee = employeeService.getEmployeeByEmail(username);

        mav.addObject("employee", employee);

        if (keyword != null) {
            List<Customer> customers = customerService.getCustomerByKeyword(keyword);
            mav.addObject("customers", customers);
            mav.addObject("keyword", keyword);
        } else {
            List<Customer> customers = customerService.getAllCustomers();
            mav.addObject("customers", customers);
            mav.addObject("keyword", keyword);
        }

        return mav;

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE','CUSTOMER')")
    @GetMapping("/customers/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Integer id, Model model, Authentication authentication) {

        ModelAndView mav = new ModelAndView("edit_customer");

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);

        mav.addObject("customer", customerService.getCustomerById(id));
        mav.addObject("employee", employee);

        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE','CUSTOMER')")
    @PostMapping("/customers/{id}")
    public ModelAndView updateCustomer(@PathVariable Integer id, Customer customer) {

        ModelAndView mav = new ModelAndView("redirect:/home");

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

        return mav;
    }
}
