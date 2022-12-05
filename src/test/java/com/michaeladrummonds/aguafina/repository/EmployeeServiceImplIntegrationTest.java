package com.michaeladrummonds.aguafina.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.michaeladrummonds.aguafina.models.Employee;
import com.michaeladrummonds.aguafina.models.User;

@DataJpaTest
public class EmployeeServiceImplIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ParameterizedTest
    @CsvSource({ "1,5 Jenifer Crossing,Lynchburg,jpowell0@hplussport.com,Jack,Powell,VA,24515,1" })
    public void testGetAllEmployees(ArgumentsAccessor accessor) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("jpowell0@hplussport.com");
        u1.setPassword("jpowell0");
        userRepository.save(u1);

        User u2 = new User();
        u2.setId(2);
        u2.setEmail("egarcia1@hplussport.com");
        u2.setPassword("egarcia1");
        userRepository.save(u2);

        Employee e = new Employee();
        e.setId(accessor.getInteger(0));
        e.setAddress(accessor.getString(1));
        e.setCity(accessor.getString(2));
        e.setEmail(accessor.getString(3));
        e.setFirstName(accessor.getString(4));
        e.setLastName(accessor.getString(5));
        e.setState(accessor.getString(6));
        e.setZipCode(accessor.getString(7));
        e.setUser(userRepository.findByEmail(accessor.getString(3)));
        employeeRepository.save(e);

        List<Employee> foundEmployees = employeeRepository.findAll();
        assertTrue(foundEmployees.contains(e));
    }

    @ParameterizedTest
    @CsvSource({ "1,5 Jenifer Crossing,Lynchburg,jpowell0@hplussport.com,Jack,Powell,VA,24515,1" })
    public void testGetEmployeeById(ArgumentsAccessor accessor) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("jpowell0@hplussport.com");
        u1.setPassword("jpowell0");
        userRepository.save(u1);

        Employee e = new Employee();
        e.setId(accessor.getInteger(0));
        e.setAddress(accessor.getString(1));
        e.setCity(accessor.getString(2));
        e.setEmail(accessor.getString(3));
        e.setFirstName(accessor.getString(4));
        e.setLastName(accessor.getString(5));
        e.setState(accessor.getString(6));
        e.setZipCode(accessor.getString(7));
        e.setUser(userRepository.findByEmail(accessor.getString(3)));
        employeeRepository.save(e);

        Employee found = employeeRepository.findById(1).get();
        assertEquals(e, found);
    }

    @ParameterizedTest
    @CsvSource({ "2,97 Vidon Alley,Manchester,egarcia1@hplussport.com,Emily,Garcia,NH,31050,2" })
    public void testGetEmployeeByEmail(ArgumentsAccessor accessor) {
        User u2 = new User();
        u2.setId(2);
        u2.setEmail("egarcia1@hplussport.com");
        u2.setPassword("egarcia1");
        userRepository.save(u2);

        Employee e = new Employee();
        e.setId(accessor.getInteger(0));
        e.setAddress(accessor.getString(1));
        e.setCity(accessor.getString(2));
        e.setEmail(accessor.getString(3));
        e.setFirstName(accessor.getString(4));
        e.setLastName(accessor.getString(5));
        e.setState(accessor.getString(6));
        e.setZipCode(accessor.getString(7));
        e.setUser(u2);
        employeeRepository.save(e);

        Employee found = employeeRepository.findByEmail("egarcia1@hplussport.com");
        assertEquals(e.getEmail(), found.getEmail());
    }
}
