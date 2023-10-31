package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  //we have certain configuration in this class, during the startup it is going to scan for all the beans that we have declared inside this class
public class SpringSecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();
//    }



    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount/**" , "/myBalance", "/myLoans" , "/myCards").authenticated()
                .requestMatchers("/notices" , "/contact" ,"/register").permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        //http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean   // it is necessary to have an encoder  when you are using jdbcUserService
//    public PasswordEncoder passwordEncoder2(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("12345"))
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager( admin,user);
//    }

//    @Bean // we can tell jdbcUserDetailManager
//    public UserDetailsService userDetailsService(DataSource dataSource){ // when you define properties file and add mysql dependency spring boot create a data source object automatically
//
//        return new JdbcUserDetailsManager(dataSource); // detail of data source is in this object ( im telling to jdbcUserDetailManager :)  )
//
//    }


}
