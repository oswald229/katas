package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public interface BankAccount {

    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    BigDecimal balance();

    AccountType type();

    Transactions getTransactions();
}
