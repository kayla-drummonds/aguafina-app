package com.michaeladrummonds.aguafina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.michaeladrummonds.aguafina.config.AuthenticatedUserService;
import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.User;
import com.michaeladrummonds.aguafina.models.UserRegistrationDto;
import com.michaeladrummonds.aguafina.models.UserRole;
import com.michaeladrummonds.aguafina.repository.UserRepository;
import com.michaeladrummonds.aguafina.repository.UserRoleRepository;
import com.michaeladrummonds.aguafina.service.impl.CustomerServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authService;

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

    @GetMapping("/registration/customer")
    public String createNewCustomerUser(Model model) {
        UserRegistrationDto registrationDto = new UserRegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "registration_customer";
    }

    @PostMapping("/registration/customer")
    public String registerCustomerUser(Model model,
            @Valid @ModelAttribute("registrationDto") UserRegistrationDto registrationDto,
            BindingResult bindingResult) {

        for (ObjectError e : bindingResult.getAllErrors()) {
            log.debug(e.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("registrationDto", registrationDto);
            return "registration_customer";
        }
        User user = new User();

        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserRole ur = new UserRole();
        ur.setRoleName("CUSTOMER");
        ur.setUserId(user.getId());

        userRoleRepository.save(ur);

        return "redirect:/registration/customer?success";
    }

    @GetMapping("/registration/employee")
    public String createNewEmployeeUser(Model model) {
        UserRegistrationDto registrationDto = new UserRegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "registration_employee";
    }

    @PostMapping("/registration/employee")
    public String registerEmployeeUser(Model model,
            @Valid @ModelAttribute("registrationDto") UserRegistrationDto registrationDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("registrationDto", registrationDto);
            return "registration_employee";
        }
        User user = new User();

        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserRole ur = new UserRole();
        ur.setRoleName("EMPLOYEE");
        ur.setUserId(user.getId());

        userRoleRepository.save(ur);

        return "redirect:/registration/employee?success";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/home")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("home");

        User user = authService.getCurrentUser();

        Employee employee = employeeService.getEmployeeByEmail(user.getEmail());
        Customer customer = customerService.getCustomerByEmail(user.getEmail());

        if (employee != null) {
            mav.addObject("employee", employee);
        } else {
            mav.addObject("customer", customer);
        }

        mav.addObject("user", user);
        return mav;
    }

}
