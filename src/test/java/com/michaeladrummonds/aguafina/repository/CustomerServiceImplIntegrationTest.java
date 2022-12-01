package com.michaeladrummonds.aguafina.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.michaeladrummonds.aguafina.models.Customer;
import com.michaeladrummonds.aguafina.models.User;

@DataJpaTest
public class CustomerServiceImplIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({ "1,'8157 Longview Court','Seattle','cshaw0@mlb.com','Carol','Shaw','206-804-8771','WA','98121',4" })
    public void testFindCustomerByEmailContaining(ArgumentsAccessor accessor) {
        User u = new User();
        u.setId(4);
        u.setEmail("cshaw0@mlb.com");
        u.setPassword("cshaw0");
        userRepository.save(u);

        Customer c = new Customer();
        c.setId(accessor.getInteger(0));
        c.setAddress(accessor.getString(1));
        c.setCity(accessor.getString(2));
        c.setEmail(accessor.getString(3));
        c.setFirstName(accessor.getString(4));
        c.setLastName(accessor.getString(5));
        c.setPhone(accessor.getString(6));
        c.setState(accessor.getString(7));
        c.setZipCode(accessor.getString(8));
        c.setUser(u);
        customerRepository.save(c);

        List<Customer> found = customerRepository.findCustomerByKeyword("cshaw");
        assertNotEquals(0, found.size());
    }

    @ParameterizedTest
    @CsvSource({
            "2,'3934 Petterle Trail','Austin','ecarr1@oracle.com','Elizabeth','Carr','512-187-2507','TX','78732',5" })
    public void testFindByEmail(ArgumentsAccessor accessor) {
        User u = new User();
        u.setId(5);
        u.setEmail("ecarr1@oracle.com");
        u.setPassword("cshaw0");
        userRepository.save(u);

        Customer c = new Customer();
        c.setId(accessor.getInteger(0));
        c.setAddress(accessor.getString(1));
        c.setCity(accessor.getString(2));
        c.setEmail(accessor.getString(3));
        c.setFirstName(accessor.getString(4));
        c.setLastName(accessor.getString(5));
        c.setPhone(accessor.getString(6));
        c.setState(accessor.getString(7));
        c.setZipCode(accessor.getString(8));
        c.setUser(u);
        customerRepository.save(c);

        Customer found = customerRepository.findByEmail(c.getEmail());
        assertEquals(c.getEmail(), found.getEmail());
    }
}
