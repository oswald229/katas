package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.math.BigDecimal;

public class CheckingAccount extends AbstractBankAccount {
    private static final AccountType accountType = AccountType.CHECKING;
    private BigDecimal overdraft = BigDecimal.ZERO;


    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return super.canWithdraw(amount)
                || canOverdraft() && isWithinOverdraft(amount);
    }

    private boolean isWithinOverdraft(BigDecimal amount) {
        return balance().subtract(amount).abs().compareTo(overdraft) <= 0;
    }

    @Override
    protected boolean canOverdraft() {
        return overdraft.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public AccountType type() {
        return accountType;
    }

}
