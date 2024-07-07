package com.kata.bank_account.domain.cases;

import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.entities.CheckingAccount;
import com.kata.bank_account.domain.entities.SavingAccount;
import com.kata.bank_account.domain.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class InMemoryAccountRepository implements AccountRepository {
    private final ArrayList<BankAccount> repositoryAccounts = new ArrayList<>();

    public InMemoryAccountRepository() {
        var savingAccount = new SavingAccount(BigDecimal.valueOf(100)) {
            @Override
            public UUID getId() {
                return UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
            }
        };
        CheckingAccount checkingAccount = new CheckingAccount() {
            @Override
            public UUID getId() {
                return UUID.fromString("98700000-8cf0-11bd-b23e-10b96e4ef00d");
            }
        };
        checkingAccount.deposit(BigDecimal.valueOf(100));

        repositoryAccounts.addAll(List.of(savingAccount, checkingAccount));
    }

    @Override
    public BankAccount getById(String id) {
        return repositoryAccounts.stream()
                .filter(account -> account.getId().equals(UUID.fromString(id)))
                .findFirst().orElseThrow();
    }

    @Override
    public void save(BankAccount account) {
        //
    }
}
