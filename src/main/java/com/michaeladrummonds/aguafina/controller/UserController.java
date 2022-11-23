package com.michaeladrummonds.aguafina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.UserRegistrationDto;
import com.michaeladrummonds.aguafina.service.impl.CustomerServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.UserServiceImpl;

@Controller
@RequestMapping
public class UserController {

    private final UserServiceImpl userService;

    private final EmployeeServiceImpl employeeService;

    private final CustomerServiceImpl customerService;

    public UserController(UserServiceImpl userService, EmployeeServiceImpl employeeService,
            CustomerServiceImpl customerService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    @GetMapping("/registration/customer")
    public String createNewCustomerUser(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_customer";
    }

    @GetMapping("/registration/employee")
    public String createNewEmployeeUser(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_employee";
    }

    @PostMapping("/registration/customer")
    public String registerCustomerUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveCustomerUser(registrationDto);
        return "redirect:/login?success";
    }

    @PostMapping("/registration/employee")
    public String registerEmployeeUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveEmployeeUser(registrationDto);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        Customer customer = customerService.getCustomerByEmail(username);

        if (employee != null) {
            model.addAttribute("employee", employee);
        } else {
            model.addAttribute("customer", customer);
        }

        model.addAttribute("username", username);
        model.addAttribute("authentication", authentication);
        return "home";
    }

}
