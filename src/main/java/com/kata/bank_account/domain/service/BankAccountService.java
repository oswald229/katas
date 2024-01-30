package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.BankAccount;

import java.math.BigDecimal;

public class BankAccountService {
    public BigDecimal deposit(BigDecimal depositAmount, BankAccount bankAccount) {
        return bankAccount.increaseBalance(depositAmount);
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount, BankAccount bankAccount) {

        if (insufficientFunds(withdrawAmount, bankAccount)) {
            throw new InsufficientFundsException();
        }

        return bankAccount.decreaseBalance(withdrawAmount);
    }

    private static boolean insufficientFunds(BigDecimal withdrawAmount, BankAccount bankAccount) {
        return bankAccount.getBalance().subtract(withdrawAmount).compareTo(BigDecimal.ZERO) < 0;
    }
}
