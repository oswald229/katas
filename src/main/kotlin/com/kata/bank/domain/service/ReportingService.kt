package com.kata.bank.domain.service

import com.kata.bank.domain.model.AccountStatement
import com.kata.bank.domain.model.BankAccount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReportingService {
    fun computeFor(account: BankAccount, reportOn: LocalDate): AccountStatement {
        val (from, reportedOn) = getReportPeriod(reportOn)
        val transactions = account.transactions
        return AccountStatement(
            transactions.totalOn(reportedOn),
            account.type(),
            transactions.occurredWithin(from, reportedOn)
        )
    }

    private fun getReportPeriod(from: LocalDate): Pair<LocalDateTime, LocalDateTime> {
        val end = LocalDateTime.of(from, LocalTime.MIDNIGHT)
        val start = end.minusMonths(1)
        return Pair(start, end)
    }


}
