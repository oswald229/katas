package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveSavingLimitException;
import com.kata.bank_account.domain.model.SavingAccount;
import com.kata.bank_account.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingAccountService implements BankAccountService {

    public BigDecimal deposit(BigDecimal depositAmount, SavingAccount bankAccount) {

        if (isAboveSavingLimit(depositAmount, bankAccount)) {
            throw new AboveSavingLimitException();
        }

        bankAccount.addTransaction(new Transaction(depositAmount, LocalDateTime.now()));

        return bankAccount.getBalance();
    }

    private static boolean isAboveSavingLimit(BigDecimal depositAmount, SavingAccount bankAccount) {
        return bankAccount.getBalance().add(depositAmount).compareTo(bankAccount.getLimit()) > 0;
    }
}
