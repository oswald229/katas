package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface FundTransaction {
    UUID id();

    BigDecimal amount();

    LocalDateTime time();

}
