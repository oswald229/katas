package com.kata.bank_account.domain.kernel.model.mocks;

import com.kata.bank_account.domain.kernel.model.BankAccount;
import com.kata.bank_account.domain.kernel.model.BankAccountType;

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
