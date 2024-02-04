package com.kata.bank_account.domain.kernel.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankAccountStatementTest {

    @Test
    void should_have_required_data() {

        BankAccountType accountType = BankAccountType.SAVING;
        LocalDate issuanceDate = LocalDate.now();
        BigDecimal balanceOn = BigDecimal.valueOf(50);
        List<Transaction> transactions = List.of();

        BankAccountStatement bankAccountStatement = new BankAccountStatement(accountType, issuanceDate, balanceOn, transactions);

        assertNotNull(bankAccountStatement.accountType());
        assertNotNull(bankAccountStatement.issuanceDate());
        assertNotNull(bankAccountStatement.balanceOnIssuanceDate());
        assertNotNull(bankAccountStatement.transactions());

    }
}
