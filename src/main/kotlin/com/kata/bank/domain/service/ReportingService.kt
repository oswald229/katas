package com.kata.bank.domain.service

import com.kata.bank.domain.model.AccountStatement
import com.kata.bank.domain.model.BankAccount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReportingService {
    fun computeFor(account: BankAccount, on: LocalDate): AccountStatement {
        val (startTime, endTime) = getReportPeriod(on)
        val transactions = account.transactions
        return AccountStatement(
            transactions.totalOn(startTime),
            account.type(),
            transactions.occurredWithin(startTime, endTime)
        )
    }

    private fun getReportPeriod(from: LocalDate): Pair<LocalDateTime, LocalDateTime> {
        val start = LocalDateTime.of(from, LocalTime.MIDNIGHT)
        val end = start.plusMonths(1)
        return Pair(start, end)
    }


}
