package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private int id;
    private String name;

    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;

    @Column(name = "create_dt")
    private String createDt;
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Cards> cards = new ArrayList<>();
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Accounts> accounts = new ArrayList<>();
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Loans> loans = new ArrayList<>();
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<AccountTransactions> accountTransactions = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roll) {
        this.role = roll;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
