package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.model.BankAccount;
import com.kata.bank_account.domain.model.BankAccountStatement;
import com.kata.bank_account.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AccountStatementService {
    public BankAccountStatement getBankStatementOn(LocalDate issuanceDate, BankAccount bankAccount) {
        List<Transaction> transactions = bankAccount.getTransactions();
        
        ArrayList<Transaction> desiredTransactions = new ArrayList<>();
        BigDecimal balanceOnIssuanceDate = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.transactionDateTime().toLocalDate();
            if (transactionDate.isBefore(issuanceDate) || transactionDate.equals(issuanceDate)) {
                desiredTransactions.add(transaction);
                balanceOnIssuanceDate = balanceOnIssuanceDate.add(transaction.amount());
            }
        }

        desiredTransactions.sort(Comparator.comparing(Transaction::transactionDateTime));

        Collections.reverse(desiredTransactions);

        return new BankAccountStatement(bankAccount.getType(), issuanceDate, balanceOnIssuanceDate, desiredTransactions);
    }
}
