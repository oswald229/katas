package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.model.BankAccountStatement;
import com.kata.bank_account.domain.model.BankAccountType;
import com.kata.bank_account.domain.model.CheckingAccount;
import com.kata.bank_account.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountStatementServiceTest {


    private final AccountStatementService accountStatementService = new AccountStatementService();

    @Test
    void should_get_bank_statement() {
        CheckingAccount checkingAccount = new CheckingAccount();


        LocalDate day = LocalDate.of(2024, 1, 1);
        LocalTime time = LocalTime.of(12, 15, 30);
        LocalDate issuanceDate = day.plusDays(1);

        Transaction transaction = new Transaction(BigDecimal.TEN, LocalDateTime.of(day, time));
        Transaction transaction1 = new Transaction(BigDecimal.ONE, LocalDateTime.of(day.plusDays(2), time));
        Transaction transaction2 = new Transaction(BigDecimal.TEN.negate(), LocalDateTime.of(day.minusDays(4), time));
        Transaction transaction3 = new Transaction(BigDecimal.valueOf(5), LocalDateTime.of(day.minusDays(4), time.minusHours(4)));

        checkingAccount.addTransaction(transaction);
        checkingAccount.addTransaction(transaction1);
        checkingAccount.addTransaction(transaction2);
        checkingAccount.addTransaction(transaction3);

        BigDecimal expectedBalance = BigDecimal.valueOf(5);
        BankAccountType expectedAccountType = BankAccountType.CHECKING;
        int expectedAmountOfTransactions = 3;

        BankAccountStatement accountStatement = accountStatementService.getBankStatementOn(issuanceDate, checkingAccount);

        assertEquals(0, accountStatement.issuanceDate().compareTo(issuanceDate));
        assertEquals(expectedAccountType, accountStatement.accountType());

        assertEquals(expectedAmountOfTransactions, accountStatement.transactions().size());

        assertEquals(expectedBalance, accountStatement.balanceOnIssuanceDate());

        assertEquals(transaction, accountStatement.transactions().get(0));
        assertEquals(transaction2, accountStatement.transactions().get(1));
        assertEquals(transaction3, accountStatement.transactions().get(2));

    }
}
