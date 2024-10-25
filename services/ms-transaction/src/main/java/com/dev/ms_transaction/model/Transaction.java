package com.dev.ms_transaction.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Transaction {

    @Id
    private String id;
    private String transactionCode;
    private Double transactionAmount;
    private DebitCard origin;
    private DebitCard destination;
    private LocalDateTime transactionDate;
    private TransactionType transactiontype;
}
