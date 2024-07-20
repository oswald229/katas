package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IBankAccount {
    UUID getId();

    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    BigDecimal balance();

    AccountType type();

    List<BankFundTransaction> getTransactions();
}
