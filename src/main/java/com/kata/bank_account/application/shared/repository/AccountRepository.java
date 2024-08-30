package com.kata.bank_account.application.shared.repository;

import com.kata.bank_account.domain.shared.model.BankAccount;

public interface AccountRepository {
    BankAccount getAccountById(String id);

    void save(BankAccount account);
}
