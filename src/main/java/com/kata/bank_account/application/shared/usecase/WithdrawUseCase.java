package com.kata.bank_account.application.shared.usecase;

import com.kata.bank_account.application.shared.model.in.WithdrawRequestModel;

public interface WithdrawUseCase {

    void execute(WithdrawRequestModel request);
}
