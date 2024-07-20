package com.kata.bank_account.domain.reporting.service;

import com.kata.bank_account.domain.reporting.model.AccountReport;
import com.kata.bank_account.domain.shared.model.BankFundTransaction;
import com.kata.bank_account.domain.shared.model.IBankAccount;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class AccountReporter {
    private static final Period DEFAULT_REPORT_PERIOD = Period.ofMonths(1);

    public AccountReport reportFor(IBankAccount bankAccount) {
        LocalDate reportDate = LocalDate.now();
        return reportFor(bankAccount, reportDate);
    }

    protected AccountReport reportFor(IBankAccount account, LocalDate reportDate) {
        var startingDate = reportDate.minus(DEFAULT_REPORT_PERIOD);
        var transactions = account.getTransactions()
                .stream()
                .filter(transaction -> {
                    var transactionDate = transaction.time().toLocalDate();
                    return isWithinRange(startingDate, reportDate, transactionDate);
                })
                .sorted(Comparator.comparing(BankFundTransaction::time).reversed())
                .toList();
        return new AccountReport(account.type(), transactions, reportDate);
    }

    private static boolean isWithinRange(LocalDate starting, LocalDate ending, LocalDate target) {
        return (target.isBefore(ending) || target.isEqual(ending))
                && (target.isAfter(starting) || target.isEqual(starting));
    }

}
