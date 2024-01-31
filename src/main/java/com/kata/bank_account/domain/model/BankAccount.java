package com.kata.bank_account.domain.model;

import java.math.BigDecimal;
import java.util.*;

public abstract class BankAccount {
    private final UUID id;
    private final ArrayList<Transaction> transactions;

    BankAccount() {
        this.id = UUID.randomUUID();
        transactions = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return transactions.stream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transactions.sort(Comparator.comparing(Transaction::transactionDateTime));
        Collections.reverse(transactions);
    }

    public abstract BankAccountType getType();
}
