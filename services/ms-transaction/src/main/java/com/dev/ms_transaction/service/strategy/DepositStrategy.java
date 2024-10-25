package com.dev.ms_transaction.service.strategy;

import com.dev.ms_transaction.model.DebitCard;
import com.dev.ms_transaction.model.Transaction;
import com.dev.ms_transaction.service.WebClientMsDebitCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DepositStrategy implements TransactionStrategy {

    private final WebClientMsDebitCard webClientMsDebitCard;

    @Override
    public Mono<Transaction> process(Transaction transaction) {
        return webClientMsDebitCard.callForDebitCard(transaction.getOrigin().getCardNumber())
                .flatMap(origin -> {
                    origin.setBalance(origin.getBalance() + transaction.getTransactionAmount());
                    transaction.setOrigin(origin);
                    return Mono.just(transaction);
                });
    }
}

