package com.michaeladrummonds.aguafina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String createNewCustomerUser(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_customer";
    }

    @PostMapping("/registration/customer")
    public String registerCustomerUser(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.saveCustomerUser(registrationDto);
            return "redirect:/login?success";
        } else {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("user", registrationDto);
            return "redirect:/registration/customer?error";
        }

    }

    @GetMapping("/registration/employee")
    public String createNewEmployeeUser(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_employee";
    }

    @PostMapping("/registration/employee")
    public ModelAndView registerEmployeeUser(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
            @ModelAttribute("bindingResult") BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("redirect:/login?success");
        if (!bindingResult.hasErrors()) {
            userService.saveEmployeeUser(registrationDto);
        } else {
            mav.addObject("bindingResult", bindingResult);
            mav.addObject("user", registrationDto);
        }
        return mav;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
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
