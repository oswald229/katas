package com.kata.bank_account.domain.model;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {

    private BigDecimal limit;

    public SavingAccount(BigDecimal limit) {
        super();
        this.limit = limit;
    }


    public BigDecimal getLimit() {
        return limit;
    }
}
