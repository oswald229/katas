package com.kata.bank_account.domain.ports.inputs;

import com.kata.bank_account.domain.kernel.model.SavingAccount;

import java.math.BigDecimal;

public interface SavingAccountServicePort {
    BigDecimal deposit(BigDecimal depositAmount, SavingAccount bankAccount);

    BigDecimal withdraw(BigDecimal withdrawAmount, SavingAccount savingAccount);
}
