package com.kata.bank_account.domain.ports.outputs;

import com.kata.bank_account.domain.kernel.model.mocks.BankAccountMock;
import com.kata.bank_account.domain.kernel.model.TransactionType;
import com.kata.bank_account.domain.adapters.BankAccountServiceAdapter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BankAccountServicePortTest {
    BankAccountServiceAdapter bankAccountServiceAdapter = new BankAccountServiceAdapter();


    @Test
    void should_add_transaction_to_bank_account() {
        BankAccountMock bankAccount = new BankAccountMock();
        BigDecimal amount = BigDecimal.TEN;

        bankAccountServiceAdapter.addTransaction(amount, bankAccount, TransactionType.DEPOSIT);
        bankAccountServiceAdapter.addTransaction(amount, bankAccount, TransactionType.WITHDRAW);

        assertEquals(2, bankAccount.getTransactions().size());

    }

    @Test
    void should_tell_if_account_has_enough_fund_for_withdraw() {
        BankAccountMock bankAccount = new BankAccountMock();

        assertFalse(bankAccountServiceAdapter.hasEnoughFundForWithdraw(BigDecimal.TEN, bankAccount));
    }
}