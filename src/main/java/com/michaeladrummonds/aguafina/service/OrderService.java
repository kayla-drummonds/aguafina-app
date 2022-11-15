package com.michaeladrummonds.aguafina.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;

@Component
public interface OrderService {

    List<Order> getAllOrders();

    Order saveOrder(Order order);

    void deleteOrderById(Integer id);

    List<Order> getOrderByCustomerId(Integer id, Customer customer);

    Double getTotalByCustomerId(Integer id, Customer customer);
    
    Double countOrdersByEmployeeId(Employee employee, Integer id);
    
    List<Order> getOrderByEmployeeId(Employee employee, Integer id);
}
