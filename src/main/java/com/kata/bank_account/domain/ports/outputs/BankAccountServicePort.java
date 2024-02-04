package com.kata.bank_account.domain.ports.outputs;

import com.kata.bank_account.domain.kernel.model.BankAccount;
import com.kata.bank_account.domain.kernel.model.Transaction;
import com.kata.bank_account.domain.kernel.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BankAccountServicePort {
    default BigDecimal addTransaction(BigDecimal transactionAmount, BankAccount bankAccount, TransactionType transactionType) {
        final LocalDateTime transactionDateTime = LocalDateTime.now();
        if (transactionType.equals(TransactionType.DEPOSIT)) {
            bankAccount.addTransaction(new Transaction(transactionAmount.abs(), transactionDateTime));
        } else if (transactionType.equals(TransactionType.WITHDRAW)) {
            bankAccount.addTransaction(new Transaction(transactionAmount.negate(), transactionDateTime));
        }

        return bankAccount.getBalance();
    }

    default boolean hasEnoughFundForWithdraw(BigDecimal withdrawAmount, BankAccount bankAccount) {
        return bankAccount.getBalance().subtract(withdrawAmount).compareTo(BigDecimal.ZERO) > 0;
    }

}
