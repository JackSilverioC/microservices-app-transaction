package com.dev.ms_debitcard.controller;

import com.dev.ms_debitcard.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DebitCardRequestDto(
        @NotNull
        String cardNumber,
        Double balance,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate expirationDate,
        @NotNull
        Client client
) {
}
