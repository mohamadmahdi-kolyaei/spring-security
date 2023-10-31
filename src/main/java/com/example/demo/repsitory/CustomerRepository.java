package com.example.demo.repsitory;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer , Long > {

    Optional<Customer> findByEmail(String email);
}
