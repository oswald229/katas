package com.kata.bank_account.application.shared.presenter;

import com.kata.bank_account.application.shared.model.out.DepositResponseModel;

public interface DepositPresenter {
    void presentSuccess(DepositResponseModel response);
}
