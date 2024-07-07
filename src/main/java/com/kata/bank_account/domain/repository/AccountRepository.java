package com.kata.bank_account.domain.repository;

import com.kata.bank_account.domain.entities.BankAccount;

public interface AccountRepository {
    BankAccount getById(String id);

    void save(BankAccount account);
}
