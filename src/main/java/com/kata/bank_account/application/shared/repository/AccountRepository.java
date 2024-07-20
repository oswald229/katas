package com.kata.bank_account.application.shared.repository;

import com.kata.bank_account.domain.shared.model.BankAccount;
import com.kata.bank_account.domain.shared.model.IBankAccount;

public interface AccountRepository {
    BankAccount getById(String id);

    void save(IBankAccount bankAccount);
}
