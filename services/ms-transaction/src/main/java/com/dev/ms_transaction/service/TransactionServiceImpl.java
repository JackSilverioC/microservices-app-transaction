package com.dev.ms_transaction.service;

import com.dev.ms_transaction.controller.TransactionResponseDto;
import com.dev.ms_transaction.exception.InsufficientBalanceException;
import com.dev.ms_transaction.kafka.KafkaProducer;
import com.dev.ms_transaction.kafka.TransferenceConfirmation;
import com.dev.ms_transaction.mapper.TransactionMapper;
import com.dev.ms_transaction.model.DebitCard;
import com.dev.ms_transaction.model.Transaction;
import com.dev.ms_transaction.model.TransactionType;
import com.dev.ms_transaction.repository.TransactionRepository;
import com.dev.ms_transaction.service.strategy.TransactionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WebClientMsDebitCard webClientMsDebitCard;
    private final KafkaProducer kafkaProducer;

    /**
     * Importante definir el strategy en TransactionStrategyConfig.java
     */
    private final Map<TransactionType, TransactionStrategy> transactionStrategies;

    @Override
    public Flux<TransactionResponseDto> findAll() {
        return transactionRepository.findAll()
                .map(transactionMapper::fromTransactionToTransactionResponseDto);
    }

    @Override
    public Mono<TransactionResponseDto> findById(String id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::fromTransactionToTransactionResponseDto);
    }

    @Override
    public Mono<TransactionResponseDto> createTransaction(Transaction transaction) {
        return transactionStrategies.get(transaction.getTransactiontype())
                .process(transaction)
                .flatMap(tr -> transactionRepository.save(tr)
                        .doOnSuccess(trOk -> {
                            // Crear y enviar la confirmación de transferencia para el origin
                            kafkaProducer.updateDebitCardBalance(
                                    new TransferenceConfirmation(
                                            trOk.getOrigin().getId(),
                                            trOk.getOrigin().getCardNumber(),
                                            trOk.getOrigin().getBalance(),
                                            trOk.getOrigin().getExpirationDate(),
                                            trOk.getOrigin().getClient()
                                    )
                            );

                            // Verificar si destination no es nulo antes de enviar la confirmación
                            if (trOk.getDestination() != null) {
                                kafkaProducer.updateDebitCardBalance(
                                        new TransferenceConfirmation(
                                                trOk.getDestination().getId(),
                                                trOk.getDestination().getCardNumber(),
                                                trOk.getDestination().getBalance(),
                                                trOk.getDestination().getExpirationDate(),
                                                trOk.getDestination().getClient()
                                        )
                                );
                            }
                        }))
                .map(transactionMapper::fromTransactionToTransactionResponseDto);
    }

}
