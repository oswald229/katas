package com.kata.bank_account.domain.entities;

import java.time.LocalDate;
import java.util.List;

public record AccountReport(AccountType accountType, List<BankTransaction> transactions, LocalDate createdOn) {
}
