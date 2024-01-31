package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveOverdraftException;
import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.CheckingAccount;
import com.kata.bank_account.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CheckingAccountService implements BankAccountService {

    protected BigDecimal deposit(BigDecimal depositAmount, CheckingAccount bankAccount) {

        bankAccount.addTransaction(new Transaction(depositAmount, LocalDateTime.now()));

        return bankAccount.getBalance();
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount, CheckingAccount bankAccount) {

        if (hasEnoughFundForWithdraw(withdrawAmount, bankAccount) || canOverdraft(withdrawAmount, bankAccount)) {

            bankAccount.addTransaction(new Transaction(withdrawAmount.negate(), LocalDateTime.now()));
            return bankAccount.getBalance();
        }

        throw new InsufficientFundsException();
    }

    private static boolean canOverdraft(BigDecimal withdrawAmount, CheckingAccount bankAccount) {
        return bankAccount.hasAllowedOverdraft() && isWithinOverdraft(withdrawAmount, bankAccount);
    }

    private static boolean isWithinOverdraft(BigDecimal withdrawAmount, CheckingAccount bankAccount) {
        boolean aboveOverdraft = bankAccount
                .getBalance()
                .subtract(withdrawAmount)
                .abs()
                .compareTo(bankAccount.getAllowedOverDraft()) > 0;
        if (aboveOverdraft) {
            throw new AboveOverdraftException();
        }
        return true;
    }

    public BigDecimal allowOverdraftOf(BigDecimal overdraftAmount, CheckingAccount bankAccount) {
        bankAccount.updateOverdraftAmount(overdraftAmount);
        return bankAccount.getAllowedOverDraft();
    }
}
