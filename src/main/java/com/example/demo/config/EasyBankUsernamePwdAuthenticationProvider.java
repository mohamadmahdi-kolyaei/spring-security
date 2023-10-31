package com.example.demo.config;

import com.example.demo.model.Customer;
import com.example.demo.repsitory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class EasyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Customer> customer = customerRepository.findByEmail(userName);
        if (customer.isPresent()){
            if (passwordEncoder.matches(pwd , customer.get().getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.get().getRoll()));
                return new UsernamePasswordAuthenticationToken(userName , pwd , authorities);
            }else {
                throw new BadCredentialsException("invalid password !!");
            }

        }else {
            throw new BadCredentialsException("no use registered with these details ");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
