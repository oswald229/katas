package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;

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

    public void deposit(BigDecimal amount) {
        this.transactions.add(new BankTransaction(amount));
    }

    public void withdraw(BigDecimal amount){
        if (canWithdraw(amount)) {
            this.transactions.add(new BankTransaction(amount.negate()));
            return;
        }
        throw new InsufficientFundsException();
    }

    protected boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0;
    }

    public BigDecimal balance() {
        return transactions
                .stream()
                .map(BankTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected abstract boolean canOverdraft();
}
