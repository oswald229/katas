package com.kata.bank_account.domain.cases;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WithdrawUseCaseTest {

    @Test
    void should_retrieve_withdraw_and_save() {
        String accountId = "98700000-8cf0-11bd-b23e-10b96e4ef00d";
        BigDecimal withdrawAmount = BigDecimal.TEN;
        var accountRepository = new InMemoryAccountRepository();

        new WithdrawUseCase(accountRepository).withdraw(accountId, withdrawAmount);

        assertThat(accountRepository.getById(accountId).balance()).isEqualTo(BigDecimal.valueOf(90));

    }
}
