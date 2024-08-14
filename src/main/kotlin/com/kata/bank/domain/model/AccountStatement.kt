package com.kata.bank.domain.model


class AccountStatement(val balance: Long, val accountType: AccountType, val transactions: List<Transaction>)
