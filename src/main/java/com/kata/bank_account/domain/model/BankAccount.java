package com.kata.bank_account.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {

    private final UUID id;
    private BigDecimal balance;

    public BankAccount() {
        this.id = UUID.randomUUID();
        this.balance = BigDecimal.ZERO;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal increaseBalance(BigDecimal depositAmount) {
        this.balance = this.balance.add(depositAmount);
        return getBalance();
    }

    public BigDecimal decreaseBalance(BigDecimal withdrawAmount) {
        this.balance = this.balance.subtract(withdrawAmount);
        return getBalance();
    }
}
