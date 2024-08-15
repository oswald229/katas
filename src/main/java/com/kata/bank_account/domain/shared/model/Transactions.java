package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transactions {
    protected List<FundTransaction> content = new ArrayList<>();

    public List<FundTransaction> occurredWithin(LocalDate start, LocalDate end) {
        return getAll()
                .stream()
                .filter(transaction -> {
                    var transactionDate = transaction.time().toLocalDate();
                    return isWithinRange(start, end, transactionDate);
                })
                .toList();
    }

    private static boolean isWithinRange(LocalDate starting, LocalDate ending, LocalDate target) {
        return (target.isBefore(ending) || target.isEqual(ending))
                && (target.isAfter(starting) || target.isEqual(starting));
    }

    protected void add(FundTransaction transaction) {
        content.add(transaction);
    }

    public BigDecimal total() {
        return content
                .stream()
                .map(FundTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<FundTransaction> getAll() {
        return Collections.unmodifiableList(content);
    }

    protected void addWithdraw(BigDecimal amount) {
        this.content.add(new Withdraw(amount));
    }

    protected void addDeposit(BigDecimal amount) {
        this.content.add(new Deposit(amount));
    }
}