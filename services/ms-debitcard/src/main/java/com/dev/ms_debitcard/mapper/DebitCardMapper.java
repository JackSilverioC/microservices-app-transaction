package com.dev.ms_debitcard.mapper;

import com.dev.ms_debitcard.controller.DebitCardRequestDto;
import com.dev.ms_debitcard.controller.DebitCardResponseDto;
import com.dev.ms_debitcard.model.DebitCard;
import org.springframework.stereotype.Component;

@Component
public class DebitCardMapper {

    public DebitCardResponseDto fromDebitCardToDebitCardResponseDto(DebitCard debitCard){
        return new DebitCardResponseDto(
                debitCard.getId(),
                debitCard.getCardNumber(),
                debitCard.getBalance(),
                debitCard.getExpirationDate(),
                debitCard.getClient()
        );
    }

    public DebitCard fromDebitCardRequestDtoToDebitCard(DebitCardRequestDto requestDto){
        return DebitCard.builder()
                .cardNumber(requestDto.cardNumber())
                .balance(requestDto.balance())
                .expirationDate(requestDto.expirationDate())
                .client(requestDto.client())
                .build();
    }
}
