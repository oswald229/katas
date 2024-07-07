package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.AboveCeilingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SavingAccountTest {

    @Test
    void should_have_ceiling() {

        BigDecimal ceiling = BigDecimal.valueOf(100);
        var savingAccount = new SavingAccount(ceiling);

        assertThat(savingAccount.ceiling()).isEqualTo(ceiling);
    }

    @Test
    void should_throw_on_deposit_greater_than_ceiling() {
        BigDecimal ceiling = BigDecimal.valueOf(100);
        var savingAccount = new SavingAccount(ceiling);
        BigDecimal depositAmount = ceiling.multiply(BigDecimal.TEN);

        assertThatThrownBy(() -> savingAccount.deposit(depositAmount))
                .isInstanceOf(AboveCeilingException.class);
    }

    @Test
    void should_allow_deposit_within_ceiling() {
        BigDecimal ceiling = BigDecimal.valueOf(100);
        var savingAccount = new SavingAccount(ceiling);

        BigDecimal depositAmount = BigDecimal.TEN;
        savingAccount.deposit(depositAmount);

        assertThat(savingAccount.balance()).isEqualTo(depositAmount);

    }

    @Test
    void should_not_have_overdraft() {
        BigDecimal ceiling = BigDecimal.valueOf(100);
        var savingAccount = new SavingAccount(ceiling);

        assertThat(savingAccount.canOverdraft()).isFalse();

    }
}
