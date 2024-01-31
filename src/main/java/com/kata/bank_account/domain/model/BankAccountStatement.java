package com.kata.bank_account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BankAccountStatement(BankAccountType accountType,
                                   LocalDate issuanceDate,
                                   BigDecimal balanceOnIssuanceDate,
                                   List<Transaction> transactions) {
}
