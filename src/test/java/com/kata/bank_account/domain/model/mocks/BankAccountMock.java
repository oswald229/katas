package com.kata.bank_account.domain.model.mocks;

import com.kata.bank_account.domain.model.BankAccount;
import com.kata.bank_account.domain.model.BankAccountType;

public class BankAccountMock extends BankAccount {
    BankAccountType type;

    public BankAccountMock() {
        super();
    }

    @Override
    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }
}
