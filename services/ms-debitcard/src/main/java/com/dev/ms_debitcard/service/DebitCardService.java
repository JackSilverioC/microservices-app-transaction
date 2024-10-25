package com.dev.ms_debitcard.service;

import com.dev.ms_debitcard.controller.DebitCardRequestDto;
import com.dev.ms_debitcard.controller.DebitCardResponseDto;
import com.dev.ms_debitcard.model.DebitCard;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface DebitCardService {

    Flux<DebitCardResponseDto> findAll();
    Mono<DebitCardResponseDto> findById(String id);
    Mono<DebitCardResponseDto> createDebitCard(DebitCardRequestDto requestDto);
    Mono<Void> deleteDebitCard(String id);
    Mono<DebitCardResponseDto> findByCardNumber(String cardNumber);

}
