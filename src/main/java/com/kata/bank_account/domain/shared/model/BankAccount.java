package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class BankAccount implements IBankAccount {
    protected UUID id;
    protected List<BankFundTransaction> transactions = new ArrayList<>();

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void deposit(BigDecimal amount) {
        this.transactions.add(new BankFundTransaction(amount));
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (canWithdraw(amount)) {
            this.transactions.add(new BankFundTransaction(amount.negate()));
            return;
        }
        throw new InsufficientFundsException();
    }

    protected boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0;
    }

    @Override
    public BigDecimal balance() {
        return transactions
                .stream()
                .map(BankFundTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected boolean canOverdraft() {
        return false;
    }

    @Override
    public List<BankFundTransaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
