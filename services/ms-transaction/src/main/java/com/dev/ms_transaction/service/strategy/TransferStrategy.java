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
public class TransferStrategy implements TransactionStrategy{

    private final WebClientMsDebitCard webClientMsDebitCard;

    @Override
    public Mono<Transaction> process(Transaction transaction) {
        return webClientMsDebitCard.callForDebitCard(transaction.getOrigin().getCardNumber())
                .zipWith(webClientMsDebitCard.callForDebitCard(transaction.getDestination().getCardNumber()))
                .flatMap(tuple -> {
                    DebitCard origin = tuple.getT1();
                    DebitCard destination = tuple.getT2();

                    if (transaction.getTransactionAmount() > origin.getBalance()) {
                        return Mono.error(new InsufficientBalanceException("Saldo insuficiente para realizar la transacción"));
                    }

                    origin.setBalance(origin.getBalance() - transaction.getTransactionAmount());
                    destination.setBalance(destination.getBalance() + transaction.getTransactionAmount());

                    transaction.setOrigin(origin);
                    transaction.setDestination(destination);

                    return Mono.just(transaction);
                });
    }
}
