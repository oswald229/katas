package com.kata.bank_account.domain.ports.inputs;

import com.kata.bank_account.domain.kernel.model.BankAccount;
import com.kata.bank_account.domain.kernel.model.BankAccountStatement;

import java.time.LocalDate;

public interface AccountStatementServicePort {
    BankAccountStatement getBankStatementOn(LocalDate issuanceDate, BankAccount bankAccount);
}
