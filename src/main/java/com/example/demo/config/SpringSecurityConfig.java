package com.example.demo.config;

import com.example.demo.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");   // When the application processes a request, the CSRF token, generated by Spring Security, is placed in a request attribute with the name you specified, in this case, "_csrf".
                                                                //In your application code, when you need to access the CSRF token, you would retrieve it from the request using this attribute name


        http.
//                securityContext(context -> context.requireExplicitSave(false))  // let spring security save authentication details inside spring security context
//                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))    spring create j session id and send it to ui app and save any session id detail
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // don't generate any J session id and http session  and i am going to do this
                .authorizeHttpRequests((requests) -> requests
//                                .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
//                                .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                                .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
//                                .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
                                .requestMatchers("/myAccount").hasRole("USER")
                                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
//                                .requestMatchers("/myLoans").hasRole("USER")
                                .requestMatchers("/myCards").hasAnyRole("USER")
                        .requestMatchers("/myAccount/**" , "/myBalance", "/myLoans" , "/myCards" ,"/user").authenticated()
                        .requestMatchers("/notices" , "/contact" ,"/register").permitAll()
                        )

//                .csrf(AbstractHttpConfigurer::disable)
                //http.csrf(csrf -> csrf.disable());
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setExposedHeaders(List.of("Authorization"));  // the header name that we are going to send as part of response to the UI app , and inside the same header we are going to send the JWT token value
                        // we are going to expose a header from back end app to a different UI app which is hosted in another origin
                        // since i am trying to expose a header  to the UI app I need to let the browser know that I am going to send a response header  please accept that otherwise my browser will not accept the header
                        // and without that header UI app can not read JWT token and can not send JWT token to the back end  whenever is trying to make a request
                        corsConfiguration.setMaxAge(3600L);
                        return corsConfiguration;

                    }
                }))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register" , "/contact")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter() , BasicAuthenticationFilter.class)  // execute csrfCookieFilter after basicAuthenticationFilter(filter of spring security whenever we are using http basic Authentication)  , after this basicAuthentication my login operation will be complete and my csrfToken will be genrated
//                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new JWTTokenGeneratorFilter() , BasicAuthenticationFilter.class)
//                .addFilterBefore(new JWTTokenValidatorFilter() , BasicAuthenticationFilter.class)

                .formLogin(withDefaults())
                .httpBasic(withDefaults());
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
