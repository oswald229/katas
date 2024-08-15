package com.kata.bank_account.domain.reporting.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.time.LocalDate;
import java.util.List;

public record AccountReport(AccountType accountType, List<com.kata.bank_account.domain.shared.model.FundTransaction> transactions, LocalDate createdOn) {
}
