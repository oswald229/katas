package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.model.BankAccount;

import java.math.BigDecimal;

public interface BankAccountService {
    default boolean hasEnoughFundForWithdraw(BigDecimal withdrawAmount, BankAccount bankAccount) {
        return bankAccount.getBalance().subtract(withdrawAmount).compareTo(BigDecimal.ZERO) > 0;
    }

}
