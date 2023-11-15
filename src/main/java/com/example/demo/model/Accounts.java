package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Accounts {

//    @Column(name = "customer_id")
//    private int customerId;

    @Id
    @Column(name="account_number")
    private long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private String createDt;
    @ManyToOne
    @JoinColumn(
            name = "Customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_ibfk_1")
    )
    private Customer customer;
    @OneToMany
    private List<AccountTransactions> accountTransactions = new ArrayList<>();



    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getBranchAddress() {
        return branchAddress;
    }
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
    public String getCreateDt() {
        return createDt;
    }
    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
