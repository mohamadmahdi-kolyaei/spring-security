package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="account_transactions")
public class AccountTransactions {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;

//    @Column(name="account_number")
//    private long accountNumber;
//
//    @Column(name = "customer_id")
//    private int customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Column(name = "create_dt")
    private String createDt;
    @ManyToOne
    @JoinColumn(
            name = "Customer_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Customer customer;
    @ManyToOne
    @JoinColumn(
            name = "account_number",
            referencedColumnName = "account_number",
            nullable = false
    )
    private Accounts accounts;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public Date getTransactionDt() {
        return transactionDt;
    }

    public void setTransactionDt(Date transactionDt) {
        this.transactionDt = transactionDt;
    }

    public String getTransactionSummary() {
        return transactionSummary;
    }

    public void setTransactionSummary(String transactionSummary) {
        this.transactionSummary = transactionSummary;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionAmt() {
        return transactionAmt;
    }

    public void setTransactionAmt(int transactionAmt) {
        this.transactionAmt = transactionAmt;
    }

    public int getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
