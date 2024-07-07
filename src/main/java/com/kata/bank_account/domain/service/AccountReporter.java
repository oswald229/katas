package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.data.AccountReport;
import com.kata.bank_account.domain.entities.BankAccount;
import com.kata.bank_account.domain.data.BankTransaction;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

public class AccountReporter {
    private static final Period DEFAULT_REPORT_PERIOD = Period.ofMonths(1);

    public AccountReport reportFor(BankAccount bankAccount) {
        LocalDate reportDate = LocalDate.now();
        return reportFor(bankAccount, reportDate);
    }

    protected AccountReport reportFor(BankAccount account, LocalDate reportDate) {
        var startingDate = reportDate.minus(DEFAULT_REPORT_PERIOD);
        var transactions = account.getTransactions()
                .stream()
                .filter(transaction -> {
                    var transactionDate = transaction.time().toLocalDate();
                    return isWithinRange(startingDate, reportDate, transactionDate);
                })
                .sorted(Comparator.comparing(BankTransaction::time).reversed())
                .toList();
        return new AccountReport(account.type(), transactions, reportDate);
    }

    private static boolean isWithinRange(LocalDate starting, LocalDate ending, LocalDate target) {
        return (target.isBefore(ending) || target.isEqual(ending))
                && (target.isAfter(starting) || target.isEqual(starting));
    }

}
