package com.kata.bank_account.domain.reporting.service;

import com.kata.bank_account.domain.reporting.model.AccountReport;
import com.kata.bank_account.domain.shared.model.FundTransaction;
import com.kata.bank_account.domain.shared.model.BankAccount;

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
        List<FundTransaction> transactions = account.getTransactions()
                .occurredWithin(reportDate, startingDate)
                .stream()
                .sorted(Comparator.comparing(FundTransaction::time).reversed())
                .toList();
        return new AccountReport(account.type(), transactions, reportDate);
    }


}
