package com.kata.bank_account.application.shared.service;

import com.kata.bank_account.application.shared.model.in.WithdrawRequestModel;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import com.kata.bank_account.application.shared.usecase.WithdrawUseCase;
import com.kata.bank_account.domain.shared.model.BankAccount;

public class WithdrawService implements WithdrawUseCase {
    private final AccountRepository accountRepository;

    public WithdrawService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute(WithdrawRequestModel request) {
        BankAccount account = accountRepository.getById(request.accountId());
        account.withdraw(request.amount());
        accountRepository.save(account);

    }
}
