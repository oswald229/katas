package com.kata.bank_account.domain.cases;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DepositUseCaseTest {


    @Test
    void should_retrieve_deposit_and_save() {
        String accountId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        BigDecimal depositAmount = BigDecimal.TEN;
        var accountRepository = new InMemoryAccountRepository();

        new DepositUseCase(accountRepository).deposit(accountId, depositAmount);

        assertThat(accountRepository.getById(accountId).balance()).isEqualTo(depositAmount);
    }

}