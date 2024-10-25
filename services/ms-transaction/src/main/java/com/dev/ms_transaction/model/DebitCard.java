package com.dev.ms_transaction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DebitCard {
    private String id;
    private String cardNumber;
    private Double balance;
    @JsonFormat(pattern = "dd/MM/yyyy")//para deserialization
    private LocalDate expirationDate;
    private Client client;
}
