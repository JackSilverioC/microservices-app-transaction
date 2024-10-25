package com.dev.ms_transaction.mapper;

import com.dev.ms_transaction.controller.TransactionRequestDto;
import com.dev.ms_transaction.controller.TransactionResponseDto;
import com.dev.ms_transaction.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDto fromTransactionToTransactionResponseDto(Transaction transaction){
        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getTransactionCode(),
                transaction.getTransactionAmount(),
                transaction.getOrigin(),
                transaction.getDestination(),
                transaction.getTransactionDate(),
                transaction.getTransactiontype()
        );
    }

    public Transaction fromTransactionRequestDtoToTransaction(TransactionRequestDto requestDto){
        return Transaction.builder()
                .transactionCode(requestDto.transactionCode())
                .transactionAmount(requestDto.transactionAmount())
                .origin(requestDto.origin())
                .destination(requestDto.destination())
                .transactionDate(requestDto.transactionDate())
                .transactiontype(requestDto.transactiontype())
                .build();
    }
}
