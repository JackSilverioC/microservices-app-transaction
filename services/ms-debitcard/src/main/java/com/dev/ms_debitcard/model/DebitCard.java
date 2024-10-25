package com.dev.ms_debitcard.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document()
@ToString
public class DebitCard {

    @Id
    private String id;
    private String cardNumber;
    private Double balance;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;
    private Client client;

}
