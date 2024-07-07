package com.kata.bank_account.domain.entities;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {
    private BigDecimal overdraft = BigDecimal.ZERO;

    CheckingAccount() {
        super();
    }

    public CheckingAccount(BigDecimal overdraft) {
        super();
        this.overdraft = overdraft;
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return balance().compareTo(amount) >= 0
                || canOverdraft() && isWithinOverdraft(amount);
    }

    private boolean isWithinOverdraft(BigDecimal amount) {
        return balance().subtract(amount).abs().compareTo(overdraft) <= 0;
    }

    @Override
    protected boolean canOverdraft() {
        return overdraft.compareTo(BigDecimal.ZERO) >= 0;
    }

}
