package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.constant.AccountType;
import com.kata.bank_account.domain.data.BankTransaction;

import java.time.LocalDate;
import java.util.List;

public record AccountReport(AccountType accountType, List<BankTransaction> transactions, LocalDate createdOn) {
}
