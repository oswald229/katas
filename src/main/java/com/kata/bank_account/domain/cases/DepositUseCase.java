package com.kata.bank_account.domain.cases;

import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.repository.AccountPersistence;

import java.math.BigDecimal;

public class DepositUseCase {

    private final AccountPersistence accountPersistence;

    public DepositUseCase(AccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    public void deposit(String accountId, BigDecimal amount) {
        BankAccount account = accountPersistence.getById(accountId);
        account.deposit(amount);
        accountPersistence.save(account);
    }
}
