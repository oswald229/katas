package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.entities.AccountReport;
import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.entities.BankTransaction;

import java.time.LocalDate;
import java.util.Comparator;

public class AccountReporter {
    public AccountReport reportFor(BankAccount bankAccount) {
        LocalDate reportDate = LocalDate.now();
        LocalDate startingDate = reportDate.minusMonths(1);

        var transactions = bankAccount.getTransactions()
                .stream()
                .filter(transaction -> {
                    var transactionDate = transaction.time().toLocalDate();
                    return (transactionDate.isBefore(reportDate) || transactionDate.isEqual(reportDate))
                            && (transactionDate.isAfter(startingDate) || transactionDate.isEqual(startingDate));
                })
                .sorted(Comparator.comparing(BankTransaction::time).reversed())
                .toList();

        return new AccountReport(bankAccount.type(), transactions, reportDate);
    }
}
