package com.kata.bank_account.domain.data;

import com.kata.bank_account.domain.constant.AccountType;

import java.time.LocalDate;
import java.util.List;

public record AccountReport(AccountType accountType, List<BankTransaction> transactions, LocalDate createdOn) {
}
