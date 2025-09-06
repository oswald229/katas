package com.kata.bank_account.application.shared.service;

import com.kata.bank_account.application.shared.model.in.DepositRequestModel;
import com.kata.bank_account.application.shared.model.out.DepositResponseModel;
import com.kata.bank_account.application.shared.presenter.DepositPresenter;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import com.kata.bank_account.application.shared.usecase.DepositUseCase;



public class DepositService implements DepositUseCase {

    private final AccountRepository repository;
    private final DepositPresenter presenter;

    public DepositService(DepositPresenter presenter, AccountRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }

    @Override
    public void execute(DepositRequestModel request) {
        var account = repository.getAccountById(request.accountId());
        account.deposit(request.amount());
        repository.save(account);

        presenter.presentSuccess(new DepositResponseModel());
    }
}
