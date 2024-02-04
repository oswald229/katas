package com.kata.bank_account.domain.kernel.model;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {

    private final BigDecimal limit;

    public SavingAccount(BigDecimal limit) {
        super();
        this.limit = limit;
    }


    public BigDecimal getLimit() {
        return limit;
    }

    @Override
    public BankAccountType getType() {
        return BankAccountType.SAVING;
    }
}
