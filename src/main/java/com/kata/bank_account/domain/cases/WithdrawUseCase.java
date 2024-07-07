package com.kata.bank_account.domain.cases;

import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.repository.AccountRepository;

import java.math.BigDecimal;

public class WithdrawUseCase {
    private final AccountRepository accountRepository;
    public WithdrawUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void withdraw(String accountId, BigDecimal amount) {
        BankAccount account = accountRepository.getById(accountId);
        account.withdraw(amount);
        accountRepository.save(account);

    }
}
