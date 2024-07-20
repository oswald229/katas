package com.kata.bank_account.application.shared.model.in;

import java.math.BigDecimal;

public record WithdrawRequestModel(String accountId, BigDecimal amount) {
}
