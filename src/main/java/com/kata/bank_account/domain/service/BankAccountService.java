package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveOverdraftException;
import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.BankAccount;

import java.math.BigDecimal;

public class BankAccountService {
    public BigDecimal deposit(BigDecimal depositAmount, BankAccount bankAccount) {
        return bankAccount.increaseBalance(depositAmount);
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount, BankAccount bankAccount) {

        if (hasEnoughFund(withdrawAmount, bankAccount) || canOverdraft(withdrawAmount, bankAccount)) {
            return bankAccount.decreaseBalance(withdrawAmount);
        }

        throw new InsufficientFundsException();
    }

    private static boolean canOverdraft(BigDecimal withdrawAmount, BankAccount bankAccount) {
        return bankAccount.hasAllowedOverdraft() && isWithinOverdraft(withdrawAmount, bankAccount);
    }

    private static boolean isWithinOverdraft(BigDecimal withdrawAmount, BankAccount bankAccount) {
        boolean aboveOverdraft = bankAccount
                .getBalance()
                .subtract(withdrawAmount)
                .abs()
                .compareTo(bankAccount.getAllowedOverDraft()) > 0 ;
        if (aboveOverdraft) {
            throw new AboveOverdraftException();
        }
        return true;
    }

    private static boolean hasEnoughFund(BigDecimal withdrawAmount, BankAccount bankAccount) {
        return bankAccount.getBalance().subtract(withdrawAmount).compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal allowOverdraftOf(BigDecimal overdraftAmount, BankAccount bankAccount) {
        bankAccount.updateOverdraftAmount(overdraftAmount);
        return bankAccount.getAllowedOverDraft();
    }
}
