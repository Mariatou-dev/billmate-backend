package com.billmate.mybillmate.models;

import com.billmate.mybillmate.enums.BillStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  String name; // Company name

    private String dueDate;

    private String initialBalance;

    private String balanceDue;

    private String amountPaid;

    private String remainingBalance;

    @Enumerated(EnumType.STRING)
    private BillStatus status; // enum

    public Bill() {
    }

    public Bill(Long id, String name, String dueDate, String initialBalance, String balanceDue, String amountPaid, String remainingBalance, BillStatus status) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.initialBalance = initialBalance;
        this.balanceDue = balanceDue;
        this.amountPaid = amountPaid;
        this.remainingBalance = remainingBalance;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getBalanceDue() {
        return balanceDue;
    }

    public void setBalance_due(String balanceDue) {
        this.balanceDue = balanceDue;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(String remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", initialBalance='" + initialBalance + '\'' +
                ", balanceDue='" + balanceDue + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", remainingBalance='" + remainingBalance + '\'' +
                ", status=" + status +
                '}';
    }
}
