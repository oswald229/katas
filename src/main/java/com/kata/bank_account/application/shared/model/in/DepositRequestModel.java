package com.kata.bank_account.application.shared.model.in;

import java.math.BigDecimal;

public record DepositRequestModel(String accountId, BigDecimal amount) {
}
