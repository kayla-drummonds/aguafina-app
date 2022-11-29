package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

@Controller
@RequestMapping
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    // displays all customers
    @GetMapping({ "/customers", "/customers/page/{pageNo}" })
    public String listCustomers(Model model,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size, Authentication authentication) {

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);

        model.addAttribute("employee", employee);

        Pageable paging = PageRequest.of(page, size);
        Page<Customer> pageCustomers = customerService.getAllCustomersPageable(paging);

        if (keyword != null) {
            List<Customer> customers = customerService.getCustomerByKeyword(keyword);
            model.addAttribute("customers", customers);
            model.addAttribute("keyword", keyword);
        } else {
            pageCustomers = customerService.getAllCustomersPageable(paging);
            List<Customer> customers = pageCustomers.getContent();
            model.addAttribute("customers", customers);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("currentPage", pageCustomers.getNumber());
        model.addAttribute("totalItems", pageCustomers.getTotalElements());
        model.addAttribute("totalPages", pageCustomers.getTotalPages());
        return "customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model, Authentication authentication) {
        model.addAttribute("customer", customerService.getCustomerById(id));

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        return "edit_customer";
    }

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
