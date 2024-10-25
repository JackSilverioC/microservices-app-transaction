package com.dev.ms_transaction.controller;

import com.dev.ms_transaction.model.Transaction;
import com.dev.ms_transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public Flux<TransactionResponseDto> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionResponseDto> findById(@PathVariable String id){
        return transactionService.findById(id);
    }

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }

}
