package com.kata.bank_account.application.service;

import com.kata.bank_account.application.shared.model.in.DepositRequestModel;
import com.kata.bank_account.application.shared.service.DepositService;
import com.kata.bank_account.application.shared.usecase.DepositUseCase;
import com.kata.bank_account.domain.shared.model.BankAccount;
import com.kata.bank_account.domain.shared.model.CheckingAccount;
import com.kata.bank_account.application.shared.presenter.DepositPresenter;
import com.kata.bank_account.application.shared.model.out.DepositResponseModel;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

class DepositServiceTest {


    @Test
    void should_retrieve_deposit_and_save() {
        String accountId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        BigDecimal depositAmount = BigDecimal.TEN;
        AccountRepository repository = Mockito.mock(AccountRepository.class);

        Mockito.when(repository.getById(accountId)).thenReturn(new CheckingAccount());
        Mockito.doNothing().when(repository).save(Mockito.any(BankAccount.class));

        var presenter = Mockito.mock(DepositPresenter.class);

        var depositRequestModel = new DepositRequestModel(accountId, depositAmount);
        DepositUseCase depositService = new DepositService(repository, presenter);
        depositService.execute(depositRequestModel);

        Mockito.verify(repository).getById(accountId);
        Mockito.verify(repository).save(Mockito.any(BankAccount.class));
        Mockito.verify(presenter).presentSuccess(Mockito.any(DepositResponseModel.class));
    }

}