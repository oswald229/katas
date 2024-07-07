package com.kata.bank_account.domain.cases;

import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.repository.AccountRepository;

import java.math.BigDecimal;

public class DepositUseCase {

    private final AccountRepository accountRepository;

    public DepositUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(String accountId, BigDecimal amount) {
        BankAccount account = accountRepository.getById(accountId);
        account.deposit(amount);
        accountRepository.save(account);
    }
}
