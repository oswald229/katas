package com.kata.bank_account.domain.shared.model;

import com.kata.bank_account.domain.shared.constant.AccountType;

import java.math.BigDecimal;

public class CheckingAccount extends AbstractBankAccount {
    private final AccountType accountType;
    private final Overdraft overdraft;


    public record Overdraft(BigDecimal amount) {
        private boolean canOverdraft() {
            return amount.compareTo(BigDecimal.ZERO) >= 0;
        }

        boolean isWithinOverdraft(BigDecimal balance, BigDecimal amount) {
            return balance.subtract(amount).abs().compareTo(amount()) <= 0;
        }
    }

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
        return overdraft.isWithinOverdraft(balance(), amount);
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
