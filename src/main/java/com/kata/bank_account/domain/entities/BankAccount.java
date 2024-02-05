package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private final UUID id;
    private List<BankTransaction> transactions;

    BankAccount() {
        id = UUID.randomUUID();
        transactions = new ArrayList<>();
    }

    public void withdraw(BigDecimal amount) {
        if (balance().compareTo(amount) >= 0) {
            this.transactions.add(new BankTransaction(amount.negate(), LocalDateTime.now()));
            return;
        }
        throw new InsufficientFundsException();
    }

    public void deposit(BigDecimal amount) {
        var bankTransaction = new BankTransaction(amount, LocalDateTime.now());
        this.transactions.add(bankTransaction);
    }

    public BigDecimal balance() {
        return transactions
                .stream()
                .map(BankTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID id() {
        return id;
    }
}
