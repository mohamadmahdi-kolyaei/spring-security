package com.example.demo.config;

import com.example.demo.model.Customer;
import com.example.demo.repsitory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// because we have created our own authentication provider we don't want dao authentication provider  do that stuff for me

//@Service   // because we have written some business logic.
//public class EasyBankUserDetails implements UserDetailsService {
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userName ,password ;
//        List<GrantedAuthority> authorities ;
//        Optional<Customer> customer = customerRepository.findByEmail(username);
//
//        if (customer.isEmpty()){
//            throw new UsernameNotFoundException("user details not found for user :" + username);
//        }else {
//            userName = customer.get().getEmail();
//            password = customer.get().getPwd();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(customer.get().getRoll()));
//        }
//
//        return new User(userName , password , authorities);
//    }
//}
