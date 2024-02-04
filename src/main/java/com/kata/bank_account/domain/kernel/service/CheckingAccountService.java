package com.kata.bank_account.domain.kernel.service;

import com.kata.bank_account.domain.kernel.exceptions.AboveOverdraftException;
import com.kata.bank_account.domain.kernel.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.kernel.model.CheckingAccount;
import com.kata.bank_account.domain.kernel.model.TransactionType;
import com.kata.bank_account.domain.ports.inputs.CheckingAccountServicePort;
import com.kata.bank_account.domain.ports.outputs.BankAccountServicePort;

import java.math.BigDecimal;

public class CheckingAccountService implements BankAccountServicePort, CheckingAccountServicePort {

    @Override
    public BigDecimal deposit(BigDecimal depositAmount, CheckingAccount bankAccount) {
        return addTransaction(depositAmount, bankAccount, TransactionType.DEPOSIT);
    }

    @Override
    public BigDecimal withdraw(BigDecimal withdrawAmount, CheckingAccount bankAccount) {
        if (hasEnoughFundForWithdraw(withdrawAmount, bankAccount) || canOverdraft(withdrawAmount, bankAccount)) {
            return addTransaction(withdrawAmount, bankAccount, TransactionType.WITHDRAW);
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
