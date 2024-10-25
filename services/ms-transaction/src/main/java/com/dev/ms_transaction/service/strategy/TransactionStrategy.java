package com.dev.ms_transaction.service.strategy;

import com.dev.ms_transaction.model.DebitCard;
import com.dev.ms_transaction.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionStrategy {
    Mono<Transaction> process(Transaction transaction);
}
