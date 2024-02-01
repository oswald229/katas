package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.model.mocks.BankAccountMock;
import com.kata.bank_account.domain.model.TransactionType;
import com.kata.bank_account.domain.service.mocks.BankAccountServiceMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BankAccountServiceTest {
    BankAccountServiceMock bankAccountServiceMock = new BankAccountServiceMock();


    @Test
    void should_add_transaction_to_bank_account() {
        BankAccountMock bankAccount = new BankAccountMock();
        BigDecimal amount = BigDecimal.TEN;

        bankAccountServiceMock.addTransaction(amount, bankAccount, TransactionType.DEPOSIT);
        bankAccountServiceMock.addTransaction(amount, bankAccount, TransactionType.WITHDRAW);

        assertEquals(2, bankAccount.getTransactions().size());

    }

    @Test
    void should_tell_if_account_has_enough_fund_for_withdraw() {
        BankAccountMock bankAccount = new BankAccountMock();

        assertFalse(bankAccountServiceMock.hasEnoughFundForWithdraw(BigDecimal.TEN, bankAccount));
    }
}