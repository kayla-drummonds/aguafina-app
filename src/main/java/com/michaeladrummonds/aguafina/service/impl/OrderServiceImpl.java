package com.michaeladrummonds.aguafina.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;
import com.michaeladrummonds.aguafina.repository.OrderRepository;
import com.michaeladrummonds.aguafina.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrderByCustomerId(Integer id, Customer customer) {
        return orderRepository.findByCustomerId(customer, id);
    }

    @Override
    public Double getTotalByCustomerId(Integer id, Customer customer) {
        return orderRepository.sumTotalByCustomer(customer, id);
    }

    @Override
    public Double countOrdersByEmployeeId(Employee employee, Integer id) {
        return orderRepository.countOrdersByEmployee(employee, id);
    }

    @Override
    public List<Order> getOrderByEmployeeId(Employee employee, Integer id) {
        return orderRepository.findByEmployeeId(employee, id);
    }

    public Page<Order> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.orderRepository.findAll(pageable);
    }

}
