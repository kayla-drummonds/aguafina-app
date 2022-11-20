package com.michaeladrummonds.aguafina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.michaeladrummonds.aguafina.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.lastName = :lastName")
    public List<Customer> findByLastName(@Param("lastName") String lastName);
}
