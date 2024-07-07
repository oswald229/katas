package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.AboveCeilingException;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {

    private final BigDecimal ceiling;

    public SavingAccount(BigDecimal ceiling) {
        this.ceiling = ceiling;
    }

    public BigDecimal ceiling() {
        return ceiling;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(ceiling) >= 0) {
            throw new AboveCeilingException();
        }
        super.deposit(amount);
    }

    public boolean canOverdraft() {
        return false;
    }
}
