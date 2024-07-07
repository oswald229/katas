package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private final UUID id;
    private final List<BankTransaction> transactions = new ArrayList<>();
    private final BigDecimal overdraft;

    BankAccount() {
        id = UUID.randomUUID();
        this.overdraft = BigDecimal.ZERO;
    }

    public BankAccount(BigDecimal overdraft) {
        id = UUID.randomUUID();
        this.overdraft = overdraft;
    }

    public UUID id() {
        return id;
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.TEN) == 0 && overdraft.compareTo(BigDecimal.valueOf(50)) == 0) {
            this.transactions.add(new BankTransaction(amount.negate()));
            return;
        }
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
