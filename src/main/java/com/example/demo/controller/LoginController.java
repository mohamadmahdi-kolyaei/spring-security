package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repsitory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity<String> response = null;
        try {
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED).body("given user details successfully added ");
            }
        }catch (Exception exception){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an exception occur due" + exception.getMessage());
        }

        return response;
    }
}
