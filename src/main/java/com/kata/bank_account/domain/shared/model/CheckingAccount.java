package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.math.BigDecimal;

public class CheckingAccount extends AbstractBankAccount {
    private final AccountType accountType;
    private final Overdraft overdraft;


    public CheckingAccount() {
        this(BigDecimal.ZERO);
    }

    public CheckingAccount(BigDecimal overdraft) {
        this.overdraft = new Overdraft(overdraft);
        this.accountType = AccountType.CHECKING;
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return super.canWithdraw(amount)
                || canOverdraft() && isWithinOverdraft(amount);
    }

    private boolean isWithinOverdraft(BigDecimal amount) {
        return overdraft.isAbove(balance().subtract(amount).abs());
    }

    @Override
    protected boolean canOverdraft() {
        return overdraft.canOverdraft();
    }

    @Override
    public AccountType type() {
        return accountType;
    }

}
