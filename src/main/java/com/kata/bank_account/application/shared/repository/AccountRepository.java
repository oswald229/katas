package com.kata.bank_account.application.shared.repository;

import com.kata.bank_account.domain.shared.model.AbstractBankAccount;
import com.kata.bank_account.domain.shared.model.BankAccount;

public interface AccountRepository {
    AbstractBankAccount getById(String id);

    void save(BankAccount bankAccount);
}
