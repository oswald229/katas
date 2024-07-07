package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private final UUID id;
    private final List<BankTransaction> transactions;

    BankAccount() {
        id = UUID.randomUUID();
        transactions = new ArrayList<>();
    }

    public UUID id() {
        return id;
    }

    public void withdraw(BigDecimal amount) {
        if (canWithdraw(amount)) {
            this.transactions.add(new BankTransaction(amount.negate()));
            return;
        }
        throw new InsufficientFundsException();
    }

    private boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0;
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
}
