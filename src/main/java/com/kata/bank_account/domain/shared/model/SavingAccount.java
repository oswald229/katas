package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;
import com.kata.bank_account.domain.shared.exception.AboveCeilingException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SavingAccount extends BankAccount {

    private static final AccountType accountType = AccountType.SAVING;
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

    @Override
    public AccountType type() {
        return accountType;
    }
}
