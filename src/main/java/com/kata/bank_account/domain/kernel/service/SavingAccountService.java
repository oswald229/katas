package com.kata.bank_account.domain.kernel.service;

import com.kata.bank_account.domain.kernel.exceptions.AboveSavingLimitException;
import com.kata.bank_account.domain.kernel.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.kernel.model.SavingAccount;
import com.kata.bank_account.domain.kernel.model.TransactionType;
import com.kata.bank_account.domain.ports.inputs.SavingAccountServicePort;
import com.kata.bank_account.domain.ports.outputs.BankAccountServicePort;

import java.math.BigDecimal;

public class SavingAccountService implements BankAccountServicePort, SavingAccountServicePort {

    @Override
    public BigDecimal deposit(BigDecimal depositAmount, SavingAccount bankAccount) {

        if (isAboveSavingLimit(depositAmount, bankAccount)) {
            throw new AboveSavingLimitException();
        }
        return addTransaction(depositAmount, bankAccount, TransactionType.DEPOSIT);
    }

    @Override
    public BigDecimal withdraw(BigDecimal withdrawAmount, SavingAccount savingAccount) {
        if (hasEnoughFundForWithdraw(withdrawAmount, savingAccount)) {
            return addTransaction(withdrawAmount, savingAccount, TransactionType.WITHDRAW);
        }
        throw new InsufficientFundsException();
    }

    private static boolean isAboveSavingLimit(BigDecimal depositAmount, SavingAccount bankAccount) {
        return bankAccount.getBalance().add(depositAmount).compareTo(bankAccount.getLimit()) > 0;
    }
}
