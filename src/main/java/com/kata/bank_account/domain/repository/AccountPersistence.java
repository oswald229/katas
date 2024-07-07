package com.kata.bank_account.domain.repository;

import com.kata.bank_account.domain.entities.BankAccount;

public interface AccountPersistence {
    BankAccount getById(String id);

    void save(BankAccount account);
}
