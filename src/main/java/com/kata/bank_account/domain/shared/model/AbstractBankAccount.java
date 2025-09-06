package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.exception.InsufficientFundsException;

import java.math.BigDecimal;

public abstract class AbstractBankAccount implements BankAccount {
    protected final Transactions transactions = new Transactions();

    public AbstractBankAccount() {
    }

    @Override
    public void deposit(BigDecimal amount) {
        this.transactions.add(new Deposit(amount));
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (canWithdraw(amount)) {
            this.transactions.add(new Withdraw(amount));
            return;
        }
        throw new InsufficientFundsException();
    }

    protected boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0;
    }

    @Override
    public BigDecimal balance() {
        return transactions.total();
    }

    protected boolean canOverdraft() {
        return false;
    }

    @Override
    public Transactions getTransactions() {
        return transactions;
    }
}
