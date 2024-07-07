package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {
    private BigDecimal overdraft = BigDecimal.ZERO;

    CheckingAccount() {
        super();
    }

    public CheckingAccount(BigDecimal overdraft) {
        super();
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (canWithdraw(amount)) {
            this.transactions.add(new BankTransaction(amount.negate()));
            return;
        }

        if (canOverdraft() && withdrawIsWithinOverdraft(amount)) {
            this.transactions.add(new BankTransaction(amount.negate()));
            return;
        }

        throw new InsufficientFundsException();
    }

    private boolean withdrawIsWithinOverdraft(BigDecimal amount) {
        return balance().subtract(amount).abs().compareTo(overdraft) <= 0;
    }

    private boolean canOverdraft() {
        return overdraft.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0;
    }

}
