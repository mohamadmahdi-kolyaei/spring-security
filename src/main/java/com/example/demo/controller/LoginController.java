package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repsitory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED).body("given user details successfully added ");
            }
        }catch (Exception exception){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an exception occur due" + exception.getMessage());
        }

        return response;
    }




    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);

    }
}


