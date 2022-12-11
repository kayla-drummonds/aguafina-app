package com.michaeladrummonds.aguafina.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.Order;
import com.michaeladrummonds.aguafina.models.User;

@DataJpaTest
public class OrderServiceImplIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("jpowell0@hplussport.com");
        u1.setPassword("jpowell0");
        userRepository.save(u1);

        User u2 = new User();
        u2.setId(4);
        u2.setEmail("cshaw0@mlb.com");
        u2.setPassword("cshaw0");
        userRepository.save(u2);

        User u3 = new User();
        u3.setId(3);
        u3.setEmail("egarcia1@hplussport.com");
        u3.setPassword("egarcia1");
        userRepository.save(u3);

        User u4 = new User();
        u4.setId(5);
        u4.setEmail("ecarr1@oracle.com");
        u4.setPassword("ecarr1");
        userRepository.save(u4);

        Employee e1 = new Employee(1, "Jack", "Powell", "jpowell0@hplussport.com", "5 Jenifer Crossing", "Lynchburg",
                "VA", "24515", null,
                u1);
        employeeRepository.save(e1);

        Employee e2 = new Employee(2, "Emily", "Garcia", "egarcia1@hplussport.com", "97 Vidon Alley", "Manchester",
                "NH", "31050", null, u3);
        employeeRepository.save(e2);

        Customer c1 = new Customer(1, "Carol", "Shaw", "cshaw0@mlb.com", "206-804-8771", "8157 Longview Court",
                "Seattle", "WA", "98121", null, u2);
        customerRepository.save(c1);

        Customer c2 = new Customer(2, "Elizabeth", "Carr", "ecarr1@oracle.com", "512-187-2507", "3934 Petterle Trail",
                "Austin", "TX", "78732", null, u4);
        customerRepository.save(c2);
    }

    @ParameterizedTest
    @CsvSource({ "1, 2022-12-02 16:07:59, MWBLU32, 3, 12, 1, 1" })
    public void testFindByCustomerId(ArgumentsAccessor accessor) throws ArgumentAccessException, ParseException {
        Order o = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setId(accessor.getInteger(0));
        o.setCreationDate(formatter.parse(accessor.getString(1)));
        o.setProduct(accessor.getString(2));
        o.setQuantity(accessor.getInteger(3));
        o.setTotal(accessor.getDouble(4));
        o.setCustomer(customerRepository.findByEmail("cshaw0@mlb.com"));
        o.setEmployee(employeeRepository.findByEmail("jpowell0@hplussport.com"));
        orderRepository.save(o);

        List<Order> found = orderRepository.findByCustomerId(o.getCustomer(), 1);
        assertEquals(1, found.size());
    }

    @ParameterizedTest
    @CsvSource({ "1, 2022-12-02 16:07:59, MWBLU32, 3, 12, 1, 1" })
    public void testSumTotalByCustomer(ArgumentsAccessor accessor) throws ArgumentAccessException, ParseException {
        Order o = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setId(accessor.getInteger(0));
        o.setCreationDate(formatter.parse(accessor.getString(1)));
        o.setProduct(accessor.getString(2));
        o.setQuantity(accessor.getInteger(3));
        o.setTotal(accessor.getDouble(4));
        o.setCustomer(customerRepository.findByEmail("cshaw0@mlb.com"));
        o.setEmployee(employeeRepository.findByEmail("jpowell0@hplussport.com"));

        orderRepository.save(o);

        Double totalFound = orderRepository.sumTotalByCustomer(o.getCustomer(), 1);
        assertEquals(12, totalFound);
    }

    @ParameterizedTest
    @CsvSource({ "2,'2022-12-02 16:08:16','MWCRA32',2,2,16,4" })
    public void testCountOrdersByEmployee(ArgumentsAccessor accessor) throws ArgumentAccessException, ParseException {
        Order o = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setId(accessor.getInteger(0));
        o.setCreationDate(formatter.parse(accessor.getString(1)));
        o.setProduct(accessor.getString(2));
        o.setTotal(accessor.getDouble(5));
        o.setCustomer(customerRepository.findByEmail("ecarr1@oracle.com"));
        o.setEmployee(employeeRepository.findByEmail("egarcia1@hplussport.com"));
        o.setQuantity(accessor.getInteger(6));
        orderRepository.save(o);

        Double countOrdersFound = orderRepository.countOrdersByEmployee(o.getEmployee(), 1);
        assertEquals(1, countOrdersFound);
    }

    @ParameterizedTest
    @CsvSource({ "2,'2022-12-02 16:08:16','MWCRA32',2,2,16,4" })
    public void testFindByEmployeeId(ArgumentsAccessor accessor) throws ArgumentAccessException, ParseException {
        Order o = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setId(accessor.getInteger(0));
        o.setCreationDate(formatter.parse(accessor.getString(1)));
        o.setProduct(accessor.getString(2));
        o.setTotal(accessor.getDouble(5));
        o.setCustomer(customerRepository.findByEmail("ecarr1@oracle.com"));
        o.setEmployee(employeeRepository.findByEmail("egarcia1@hplussport.com"));
        o.setQuantity(accessor.getInteger(6));
        orderRepository.save(o);

        List<Order> ordersFound = orderRepository.findByEmployeeId(o.getEmployee(), 2);
        assertEquals(1, ordersFound.size());
    }

    @ParameterizedTest
    @CsvSource({ "1,'2022-12-02 16:07:59','MWBLU32',1,1,12,3" })
    public void testDeleteById(ArgumentsAccessor accessor)
            throws ArgumentAccessException, ParseException, EmptyResultDataAccessException {
        Order o = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        o.setId(accessor.getInteger(0));
        o.setCreationDate(formatter.parse(accessor.getString(1)));
        o.setProduct(accessor.getString(2));
        o.setTotal(accessor.getDouble(5));
        o.setCustomer(customerRepository.findByEmail("cshaw0@mlb.com"));
        o.setEmployee(employeeRepository.findByEmail("jpowell0@hplussport.com"));
        o.setQuantity(accessor.getInteger(6));

        orderRepository.save(o);

        orderRepository.deleteById(o.getId());
        List<Order> orders = orderRepository.findByCustomerId(o.getCustomer(), 1);
        assertTrue(orders.isEmpty());
    }
}
