package com.kata.bank_account.application.shared.service;

import com.kata.bank_account.application.shared.model.in.DepositRequestModel;
import com.kata.bank_account.application.shared.model.out.DepositResponseModel;
import com.kata.bank_account.application.shared.presenter.DepositPresenter;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import com.kata.bank_account.application.shared.usecase.DepositUseCase;

public class DepositService implements DepositUseCase {

    private final AccountRepository accountRepository;
    private final DepositPresenter presenter;

    public DepositService(AccountRepository accountRepository, DepositPresenter presenter) {
        this.accountRepository = accountRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(DepositRequestModel request) {
        var account = accountRepository.getById(request.accountId());
        account.deposit(request.amount());
        accountRepository.save(account);

        presenter.presentSuccess(new DepositResponseModel());
    }
}
