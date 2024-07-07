package com.kata.bank_account.domain.cases;

import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.entities.SavingAccount;
import com.kata.bank_account.domain.repository.AccountPersistence;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class DepositUseCaseTest {


    @Test
    void should_retrieve_deposit_and_save() {
        String accountId = "accountId";
        SavingAccount savingAccount = new SavingAccount(BigDecimal.valueOf(100));

        var savedAccounts = new ArrayList<BankAccount>();

        var accountRepository = new AccountPersistence() {
            @Override
            public BankAccount getById(String id) {
                return savingAccount;
            }

            @Override
            public void save(BankAccount account) {
                savedAccounts.add(account);
            }
        };

        var depositUseCase = new DepositUseCase(accountRepository);

        BigDecimal depositAmount = BigDecimal.TEN;
        depositUseCase.deposit(accountId, depositAmount);

        assertThat(savedAccounts)
                .containsOnly(savingAccount)
                .allSatisfy(bankAccount -> assertThat(bankAccount.balance()).isEqualTo(depositAmount));
    }
}