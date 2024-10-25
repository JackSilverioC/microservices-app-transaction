package com.dev.ms_transaction.service.strategy;

import com.dev.ms_transaction.exception.InsufficientBalanceException;
import com.dev.ms_transaction.model.DebitCard;
import com.dev.ms_transaction.model.Transaction;
import com.dev.ms_transaction.service.WebClientMsDebitCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CashOutStrategy implements TransactionStrategy{

    private final WebClientMsDebitCard webClientMsDebitCard;

    @Override
    public Mono<Transaction> process(Transaction transaction) {
        return webClientMsDebitCard.callForDebitCard(transaction.getOrigin().getCardNumber())
                .flatMap(origin -> {
                    if (transaction.getTransactionAmount() > origin.getBalance()) {
                        return Mono.error(new InsufficientBalanceException("Saldo insuficiente para realizar la transacción"));
                    }
                    origin.setBalance(origin.getBalance() - transaction.getTransactionAmount());
                    transaction.setOrigin(origin);
                    return Mono.just(transaction);
                });
    }

}
