package com.michaeladrummonds.aguafina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/orders")
    public String listAllOrders(Model model, Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        return findPaginated(1, "id", "asc", model, authentication);
    }

    @GetMapping("/orders/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir, Model model, Authentication authentication) {

        String username = authentication.getName();
        Employee employee = employeeService.getEmployeeByEmail(username);
        model.addAttribute("employee", employee);

        int pageSize = 10;
        Page<Order> page = orderService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Order> orders = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/new")
    public String createOrder(Model model) {
        Order order = new Order();
        List<Customer> customers = customerService.getAllCustomers();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("order", order);
        model.addAttribute("customers", customers);
        model.addAttribute("employees", employees);
        return "create_order";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrderById(@PathVariable Integer id) {

        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }

    @GetMapping("/orders/customer/{customer}")
    public String getOrdersByCustomer(Model model, @Param("customer") @ModelAttribute("customer") Customer customer) {
        Integer customerId = customer.getId();
        List<Order> ordersByCustomer = orderService.getOrderByCustomerId(customerId, customer);
        Double total = orderService.getTotalByCustomerId(customerId, customer);
        model.addAttribute("ordersByCustomer", ordersByCustomer);
        model.addAttribute("total", total);
        return "customer_orders";
    }

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
