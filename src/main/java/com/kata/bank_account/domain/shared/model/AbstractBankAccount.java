package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.exception.InsufficientFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class AbstractBankAccount implements BankAccount {
    @Getter
    protected UUID id;
    protected final Transactions transactions = new Transactions();


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
