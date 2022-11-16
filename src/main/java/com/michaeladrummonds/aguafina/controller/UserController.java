package com.michaeladrummonds.aguafina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeladrummonds.aguafina.models.UserRegistrationDto;
import com.michaeladrummonds.aguafina.service.impl.UserServiceImpl;

@Controller
@RequestMapping
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/registration/user")
    public String createNewUser(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_user";
    }

    @GetMapping("/registration/admin")
    public String createNewAdmin(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration_admin";
    }

    @PostMapping("/registration/user")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
        return "redirect:/login?success";
    }

    @PostMapping("/registration/admin")
    public String registerAdmin(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveAdmin(registrationDto);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", request);
        return "login";
    }

    @GetMapping("/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    @GetMapping("/user")
    public ResponseEntity<String> defaultPage(Model model) {
        return new ResponseEntity<>("You have USER role.", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAllBlogs(Model model) {
        return new ResponseEntity<>("You have ADMIN role.", HttpStatus.OK);
    }
}
