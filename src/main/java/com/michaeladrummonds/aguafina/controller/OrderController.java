package com.michaeladrummonds.aguafina.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.michaeladrummonds.aguafina.config.AuthenticatedUserService;
import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;
import com.michaeladrummonds.aguafina.models.User;
import com.michaeladrummonds.aguafina.service.impl.CustomerServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AuthenticatedUserService authService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders")
    public ModelAndView listAllOrders() {
        ModelAndView mav = new ModelAndView("orders");

        User user = authService.getCurrentUser();
        Employee employee = employeeService.getEmployeeByEmail(user.getEmail());

        List<Order> orders = orderService.getAllOrders();
        Customer customer = new Customer();

        mav.addObject("employee", employee);
        mav.addObject("orders", orders);
        mav.addObject("customer", customer);

        log.debug("There are currently " + orders.size() + " completed orders.");
        orders.stream()
                .forEach(x -> log.debug(x.getId() + " | " + x.getProduct() + " | " +
                        x.getCreationDate()));
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders/new")
    public String createOrder(Model model) {

        User user = authService.getCurrentUser();
        Employee employee = employeeService.getEmployeeByEmail(user.getEmail());

        Order order = new Order();
        List<Customer> customers = customerService.getAllCustomers();
        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employee", employee);
        model.addAttribute("order", order);
        model.addAttribute("customers", customers);
        model.addAttribute("employees", employees);
        return "create_order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/orders")
    public String saveOrder(Model model, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult) {

        for (ObjectError e : bindingResult.getAllErrors()) {
            log.debug(e.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            List<Customer> customers = customerService.getAllCustomers();
            List<Employee> employees = employeeService.getAllEmployees();
            model.addAttribute("customers", customers);
            model.addAttribute("employees", employees);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("order", order);
            return "create_order";
        } else {
            order.setCreationDate(new Date());
            orderService.saveOrder(order);

            log.debug(order.toString());
            return "redirect:/orders";
        }

    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/orders/delete/{id}")
    public ModelAndView deleteOrderById(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("redirect:/orders");

        orderService.deleteOrderById(id);

        log.debug("order with id: " + id + " has been deleted.");
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @GetMapping("/orders/customer/{customer}")
    public ModelAndView getOrdersByCustomer(@Param("customer") Customer customer) {
        ModelAndView mav = new ModelAndView("customer_orders");

        User user = authService.getCurrentUser();

        Integer customerId = customer.getId();
        List<Order> ordersByCustomer = orderService.getOrderByCustomerId(customerId, customer);
        Double total = orderService.getTotalByCustomerId(customerId, customer);

        try {
            if (user.getEmail().equals(customer.getEmail())) {
                mav.addObject("customer", customer);
                if (total != null && !ordersByCustomer.isEmpty()) {
                    mav.addObject("total", total);
                    mav.addObject("ordersByCustomer", ordersByCustomer);
                } else {
                    total = 0.00;
                    mav.addObject("total", total);
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }

        log.debug(customer.getFirstName() + " " + customer.getLastName() + " has " + ordersByCustomer.size()
                + " orders.");

        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders/employee/{employee}")
    public ModelAndView getOrdersByEmployee(@Param("employee") Employee employee) {
        ModelAndView mav = new ModelAndView("employee_orders");

        Integer employeeId = employee.getId();
        List<Order> ordersByEmployee = orderService.getOrderByEmployeeId(employee, employeeId);
        Double orderCount = orderService.countOrdersByEmployeeId(employee, employeeId);

        mav.addObject("ordersByEmployee", ordersByEmployee);
        mav.addObject("orderCount", orderCount);

        log.debug(employee.getFirstName() + " " + employee.getLastName() + " has " + ordersByEmployee.size()
                + " orders.");

        return mav;
    }
}
