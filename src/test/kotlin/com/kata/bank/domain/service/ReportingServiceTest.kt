package com.kata.bank.domain.service

import com.kata.bank.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReportingServiceTest {


    @Test
    fun should_compute_report() {
        val transactions = Transactions()
        val januaryFirst = LocalDate.of(2024, 1, 1)
        val transaction = Transaction(100, LocalDateTime.of(januaryFirst, LocalTime.MIDNIGHT))
        transactions.add(transaction)
        val transaction1 = Transaction(100, LocalDateTime.of(januaryFirst.plusMonths(1), LocalTime.MIDNIGHT))
        transactions.add(transaction1)
        val transaction2 = Transaction(100, LocalDateTime.of(januaryFirst.plusMonths(2), LocalTime.MIDNIGHT))
        transactions.add(transaction2)

        val reportingService = ReportingService()
        val bankAccount = InMemoryBankAccount()
        bankAccount.mockTransactions(transactions)

        val statement: AccountStatement = reportingService.computeFor(bankAccount, januaryFirst)

        assertThat(statement.balance).isEqualTo(100)
        assertThat(statement.accountType).isEqualTo(AccountType.CHECKING)
        assertThat(statement.transactions).containsExactly(transaction)
    }

    @Test
    fun should_compute_report_for_saving_account() {
        val transactions = Transactions()
        val januaryFirst = LocalDate.of(2024, 1, 1)
        val transaction = Transaction(100, LocalDateTime.of(januaryFirst, LocalTime.MIDNIGHT))
        transactions.add(transaction)
        val transaction1 = Transaction(100, LocalDateTime.of(januaryFirst.plusMonths(1), LocalTime.MIDNIGHT))
        transactions.add(transaction1)
        val transaction2 = Transaction(50, LocalDateTime.of(januaryFirst.plusMonths(2), LocalTime.MIDNIGHT))
        transactions.add(transaction2)

        val reportingService = ReportingService()
        val bankAccount = InMemorySavingAccount()

        bankAccount.mockTransactions(transactions)

        val statement: AccountStatement = reportingService.computeFor(bankAccount, januaryFirst.plusMonths(1))

        assertThat(statement.balance).isEqualTo(200)
        assertThat(statement.accountType).isEqualTo(AccountType.SAVING)
        assertThat(statement.transactions).containsExactly(transaction, transaction1)
    }

}