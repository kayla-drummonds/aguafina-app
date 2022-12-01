package com.michaeladrummonds.aguafina.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;
import com.michaeladrummonds.aguafina.service.impl.CustomerServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.EmployeeServiceImpl;
import com.michaeladrummonds.aguafina.service.impl.OrderServiceImpl;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders")
    public String listAllOrders(Model model, Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders/new")
    public String createOrder(Model model, Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        Order order = new Order();
        List<Customer> customers = customerService.getAllCustomers();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("order", order);
        model.addAttribute("customers", customers);
        model.addAttribute("employees", employees);
        return "create_order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute("order") Order order) {
        order.setCreationDate(new Date());
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/orders/delete/{id}")
    public String deleteOrderById(@PathVariable Integer id) {

        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @GetMapping("/orders/customer/{customer}")
    public String getOrdersByCustomer(Model model, @Param("customer") @ModelAttribute("customer") Customer customer) {
        Integer customerId = customer.getId();
        List<Order> ordersByCustomer = orderService.getOrderByCustomerId(customerId, customer);
        Double total = orderService.getTotalByCustomerId(customerId, customer);
        model.addAttribute("ordersByCustomer", ordersByCustomer);
        model.addAttribute("total", total);
        return "customer_orders";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/orders/employee/{employee}")
    public String getOrdersByEmployee(Model model, @Param("employee") @ModelAttribute("employee") Employee employee) {
        Integer employeeId = employee.getId();
        List<Order> ordersByEmployee = orderService.getOrderByEmployeeId(employee, employeeId);
        Double orderCount = orderService.countOrdersByEmployeeId(employee, employeeId);
        model.addAttribute("ordersByEmployee", ordersByEmployee);
        model.addAttribute("orderCount", orderCount);
        return "employee_orders";
    }
}
