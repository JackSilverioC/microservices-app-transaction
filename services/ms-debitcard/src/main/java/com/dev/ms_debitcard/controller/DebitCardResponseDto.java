package com.dev.ms_debitcard.controller;

import com.dev.ms_debitcard.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DebitCardResponseDto(
        String id,
        String cardNumber,
        Double balance,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate expirationDate,
        Client client
) {
}
