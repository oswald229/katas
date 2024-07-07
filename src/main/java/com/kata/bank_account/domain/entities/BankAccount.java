package com.kata.bank_account.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BankAccount {
    protected final UUID id;
    protected final List<BankTransaction> transactions = new ArrayList<>();

    protected BankAccount() {
        id = UUID.randomUUID();
    }

    public UUID id() {
        return id;
    }

    public void deposit(BigDecimal amount) {
        this.transactions.add(new BankTransaction(amount));
    }

    public BigDecimal balance() {
        return transactions
                .stream()
                .map(BankTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public abstract void withdraw(BigDecimal amount);
}
