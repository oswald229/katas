package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveSavingLimitException;
import com.kata.bank_account.domain.model.SavingAccount;

import java.math.BigDecimal;

public class SavingAccountService implements BankAccountService {

    public BigDecimal deposit(BigDecimal depositAmount, SavingAccount bankAccount) {

        if (isAboveSavingLimit(depositAmount, bankAccount)) {
            throw new AboveSavingLimitException();
        }

        return bankAccount.increaseBalance(depositAmount);
    }

    private static boolean isAboveSavingLimit(BigDecimal depositAmount, SavingAccount bankAccount) {
        return bankAccount.getBalance().add(depositAmount).compareTo(bankAccount.getLimit()) > 0;
    }
}
