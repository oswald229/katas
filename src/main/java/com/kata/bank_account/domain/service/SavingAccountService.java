package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveSavingLimitException;
import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.SavingAccount;
import com.kata.bank_account.domain.model.TransactionType;

import java.math.BigDecimal;

public class SavingAccountService implements BankAccountService {

    public BigDecimal deposit(BigDecimal depositAmount, SavingAccount bankAccount) {

        if (isAboveSavingLimit(depositAmount, bankAccount)) {
            throw new AboveSavingLimitException();
        }
        return addTransaction(depositAmount, bankAccount, TransactionType.DEPOSIT);
    }

    private static boolean isAboveSavingLimit(BigDecimal depositAmount, SavingAccount bankAccount) {
        return bankAccount.getBalance().add(depositAmount).compareTo(bankAccount.getLimit()) > 0;
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount, SavingAccount savingAccount) {
        if (hasEnoughFundForWithdraw(withdrawAmount, savingAccount)) {
            return addTransaction(withdrawAmount, savingAccount, TransactionType.WITHDRAW);
        }
        throw new InsufficientFundsException();
    }
}
