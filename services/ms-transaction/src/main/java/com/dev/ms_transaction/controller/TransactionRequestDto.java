package com.dev.ms_transaction.controller;

import com.dev.ms_transaction.model.DebitCard;
import com.dev.ms_transaction.model.TransactionType;

import java.time.LocalDateTime;

public record TransactionRequestDto(
        String transactionCode,
        Double transactionAmount,
        DebitCard origin,
        DebitCard destination,
        LocalDateTime transactionDate,
        TransactionType transactiontype
) {
}
