package com.michaeladrummonds.aguafina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView createNewCustomerUser() {
        ModelAndView mav = new ModelAndView("registration_customer");

        UserRegistrationDto user = new UserRegistrationDto();
        mav.addObject("user", user);

        return mav;
    }

    @PostMapping("/registration/customer")
    public ModelAndView registerCustomerUser(@Valid UserRegistrationDto registrationDto,
            BindingResult bindingResult) {

        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("redirect:/registration/customer?error");
            return mav;
        }
        userService.saveCustomerUser(registrationDto);
        mav.setViewName("redirect:/registration/customer?success");

        return mav;
    }

    @GetMapping("/registration/employee")
    public ModelAndView createNewEmployeeUser() {
        ModelAndView mav = new ModelAndView("registration_employee");
        UserRegistrationDto user = new UserRegistrationDto();
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/registration/employee")
    public ModelAndView registerEmployeeUser(@Valid UserRegistrationDto registrationDto,
            BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("redirect:/registration/employee?error");
            return mav;
        }
        userService.saveCustomerUser(registrationDto);
        mav.setViewName("redirect:/registration/employee?success");

        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            mav.setViewName("login");
            return mav;
        }

        mav.setViewName("redirect:/home");
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/home")
    public ModelAndView getHomePage(Authentication authentication) {
        ModelAndView mav = new ModelAndView("home");

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        Customer customer = customerService.getCustomerByEmail(username);

        if (employee != null) {
            mav.addObject("employee", employee);
        } else {
            mav.addObject("customer", customer);
        }

        mav.addObject("username", username);
        mav.addObject("authentication", authentication);
        return mav;
    }

}
