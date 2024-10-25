package com.dev.ms_transaction.kafka;

import com.dev.ms_transaction.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TransferenceConfirmation(
        String id,
        String cardNumber,
        Double balance,
        @JsonFormat(pattern = "dd/MM/yyyy")//para deserialization
        LocalDate expirationDate,
        Client client
) {
}
