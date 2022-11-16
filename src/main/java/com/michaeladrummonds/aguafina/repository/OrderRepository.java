package com.michaeladrummonds.aguafina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.customer = ?1")
    List<Order> findByCustomerId(Customer customer, Integer id);

    @Query("select sum(o.total) from Order o where o.customer = ?1")
    Double sumTotalByCustomer(Customer customer, Integer id);

    @Query("select count(o) from Order o where o.employee = ?1")
    Double countOrdersByEmployee(Employee employee, Integer id);

    @Query("select o from Order o where o.employee = ?1")
    List<Order> findByEmployeeId(Employee employee, Integer id);

}
