package com.dev.ms_transaction.service;

import com.dev.ms_transaction.controller.TransactionResponseDto;
import com.dev.ms_transaction.model.Transaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TransactionService {

    Flux<TransactionResponseDto> findAll();
    Mono<TransactionResponseDto> findById(String id);
    Mono<TransactionResponseDto> createTransaction(Transaction transaction);


}
