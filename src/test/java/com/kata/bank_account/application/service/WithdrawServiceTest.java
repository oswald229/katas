package com.kata.bank_account.application.service;

import com.kata.bank_account.application.shared.model.in.WithdrawRequestModel;
import com.kata.bank_account.application.shared.service.WithdrawService;
import com.kata.bank_account.domain.shared.model.AbstractBankAccount;
import com.kata.bank_account.domain.shared.model.CheckingAccount;
import com.kata.bank_account.application.shared.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WithdrawServiceTest {

    @Test
    void should_retrieve_withdraw_and_save() {
        var accountId = "98700000-8cf0-11bd-b23e-10b96e4ef00d";
        var withdrawAmount = BigDecimal.TEN;
        var accountRepository = Mockito.mock(AccountRepository.class);

        var account = new CheckingAccount();
        account.deposit(BigDecimal.valueOf(100));

        Mockito.when(accountRepository.getById(accountId)).thenReturn(account);
        Mockito.doNothing().when(accountRepository).save(Mockito.any(AbstractBankAccount.class));

        new WithdrawService(accountRepository)
                .execute(new WithdrawRequestModel(accountId, withdrawAmount));

        Mockito.verify(accountRepository).getById(accountId);
        Mockito.verify(accountRepository).save(Mockito.any(AbstractBankAccount.class));
        assertThat(account.balance()).isEqualTo(BigDecimal.valueOf(90));

    }
}
