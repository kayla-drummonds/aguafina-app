package com.michaeladrummonds.aguafina.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders")
    public ModelAndView listAllOrders(Authentication authentication) {
        ModelAndView mav = new ModelAndView("orders");

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        List<Order> orders = orderService.getAllOrders();

        mav.addObject("employee", employee);
        mav.addObject("orders", orders);

        log.debug("There are currently " + orders.size() + " completed orders.");

        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders/new")
    public ModelAndView createOrder(Authentication authentication) {
        ModelAndView mav = new ModelAndView("create_order");

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        Order order = new Order();
        List<Customer> customers = customerService.getAllCustomers();
        List<Employee> employees = employeeService.getAllEmployees();

        mav.addObject("employee", employee);
        mav.addObject("order", order);
        mav.addObject("customers", customers);
        mav.addObject("employees", employees);
        return mav;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/orders")
    public ModelAndView saveOrder(Order order) {
        ModelAndView mav = new ModelAndView("redirect:/orders");

        order.setCreationDate(new Date());
        orderService.saveOrder(order);

        log.debug(order.toString());

        return mav;
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

        Integer customerId = customer.getId();
        List<Order> ordersByCustomer = orderService.getOrderByCustomerId(customerId, customer);
        Double total = orderService.getTotalByCustomerId(customerId, customer);

        mav.addObject("ordersByCustomer", ordersByCustomer);
        mav.addObject("total", total);

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
