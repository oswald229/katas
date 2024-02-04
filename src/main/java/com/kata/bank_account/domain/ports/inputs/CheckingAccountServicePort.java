package com.kata.bank_account.domain.ports.inputs;

import com.kata.bank_account.domain.kernel.model.CheckingAccount;

import java.math.BigDecimal;

public interface CheckingAccountServicePort {
    BigDecimal deposit(BigDecimal depositAmount, CheckingAccount bankAccount);

    BigDecimal withdraw(BigDecimal withdrawAmount, CheckingAccount bankAccount);
}
