package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;

public record Overdraft(BigDecimal amount) {
    public boolean canOverdraft() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    boolean isAbove(BigDecimal abs) {
        return abs.compareTo(amount()) <= 0;
    }
}
