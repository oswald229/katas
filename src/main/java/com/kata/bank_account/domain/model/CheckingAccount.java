package com.kata.bank_account.domain.model;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {


    private BigDecimal allowedOverdraft = BigDecimal.ZERO;

    public CheckingAccount() {
        super();
    }

    public boolean hasAllowedOverdraft() {
        return allowedOverdraft.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getAllowedOverDraft() {
        return allowedOverdraft;
    }

    public void updateOverdraftAmount(BigDecimal overdraftAmount) {
        this.allowedOverdraft = overdraftAmount;
    }


}
