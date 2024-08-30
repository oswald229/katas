package com.kata.bank_account.application.service;

import com.kata.bank_account.application.shared.model.in.DepositRequestModel;
import com.kata.bank_account.application.shared.service.DepositService;
import com.kata.bank_account.domain.shared.model.BankAccount;
import com.kata.bank_account.domain.shared.model.CheckingAccount;
import com.kata.bank_account.application.shared.presenter.DepositPresenter;
import com.kata.bank_account.application.shared.model.out.DepositResponseModel;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepositServiceTest {


    @Test
    void should_retrieve_deposit_and_save() {
        var accountId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        var depositAmount = BigDecimal.TEN;
        var repository = mock(AccountRepository.class);

        when(repository.getAccountById(accountId)).thenReturn(new CheckingAccount());
        doNothing().when(repository).save(any(BankAccount.class));
        var presenter = mock(DepositPresenter.class);
        var depositRequestModel = new DepositRequestModel(accountId, depositAmount);

        new DepositService(repository, presenter).execute(depositRequestModel);

        verify(repository).getAccountById(accountId);
        verify(repository).save(any(BankAccount.class));
        verify(presenter).presentSuccess(any(DepositResponseModel.class));
    }

}