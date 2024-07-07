package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.constant.AccountType;
import com.kata.bank_account.domain.data.BankTransaction;
import com.kata.bank_account.domain.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AccountReporterTest {

    @ParameterizedTest
    @MethodSource("accountTypes")
    void should_tell_bank_account_type(BankAccount account, AccountType expectedType) {

        var accountReporter = new AccountReporter();

        AccountReport report = accountReporter.reportFor(account);

        assertThat(report.accountType()).isEqualTo(expectedType);

    }

    @Test
    void should_report_transactions_within_sliding_month() {
        var accountReporter = new AccountReporter();

        LocalDate today = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0);

        var todayTransaction = new BankTransaction(BigDecimal.TEN, LocalDateTime.of(
                today, midnight
        ));
        var tenDaysAgoTransaction = new BankTransaction(BigDecimal.TEN, LocalDateTime.of(
                today.minusDays(10), midnight
        ));
        var oneMonthAgoTransaction = new BankTransaction(BigDecimal.TEN, LocalDateTime.of(
                today.minusMonths(1), midnight
        ));
        var aYearAgoTransaction = new BankTransaction(BigDecimal.TEN, LocalDateTime.of(
                today.minusYears(1), midnight
        ));

        var bankAccount = new CheckingAccount() {
            @Override
            public List<BankTransaction> getTransactions() {
                return List.of(todayTransaction,
                        tenDaysAgoTransaction,
                        oneMonthAgoTransaction,
                        aYearAgoTransaction
                );
            }
        };


        AccountReport report = accountReporter.reportFor(bankAccount);

        assertThat(report.createdOn()).isEqualTo(today);
        assertThat(report.accountType()).isEqualTo(AccountType.CHECKING);
        assertThat(report.transactions()).containsExactly(todayTransaction, tenDaysAgoTransaction, oneMonthAgoTransaction);
    }

    @Test
    void should_report_transactions_within_sliding_month_bis() {
        var accountReporter = new AccountReporter();

        LocalDate today = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0);

        var aYearAgoTransaction = new BankTransaction(BigDecimal.TEN, LocalDateTime.of(
                today.minusYears(1), midnight
        ));

        var bankAccount = new BankAccount() {
            @Override
            public AccountType type() {
                return AccountType.SAVING;
            }

            @Override
            public List<BankTransaction> getTransactions() {
                return List.of(
                        aYearAgoTransaction
                );
            }
        };
        LocalDate reportDate = today.minusMonths(5);

        AccountReport report = accountReporter.reportFor(bankAccount, reportDate);

        assertThat(report.createdOn()).isEqualTo(reportDate);
        assertThat(report.accountType()).isEqualTo(AccountType.SAVING);
        assertThat(report.transactions()).isEmpty();
    }

    public static Stream<Arguments> accountTypes() {
        return Stream.of(
                Arguments.of(new CheckingAccount(), AccountType.CHECKING),
                Arguments.of(new SavingAccount(BigDecimal.ZERO), AccountType.SAVING)
        );
    }

}
