package com.kata.bank_account.application.shared.usecase;

import com.kata.bank_account.application.shared.model.in.DepositRequestModel;

public interface DepositUseCase {

    void execute(DepositRequestModel request);
}
