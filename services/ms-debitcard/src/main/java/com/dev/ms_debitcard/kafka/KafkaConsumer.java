package com.dev.ms_debitcard.kafka;

import com.dev.ms_debitcard.model.DebitCard;
import com.dev.ms_debitcard.repository.DebitCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final DebitCardRepository debitCardRepository;

    @KafkaListener(topics = "transaction-topic", groupId = "debitCardGroup")
    public void getDebitCardOperation(TransferenceConfirmation transferenceConfirmation) throws MessagingException {
        log.info("Consuming the message from transaction-topic: {}", transferenceConfirmation);
        debitCardRepository.save(
                DebitCard.builder()
                        .id(transferenceConfirmation.id())
                        .cardNumber(transferenceConfirmation.cardNumber())
                        .balance(transferenceConfirmation.balance())
                        .expirationDate(transferenceConfirmation.expirationDate())
                        .client(transferenceConfirmation.client())
                        .build()
        ).subscribe(
                result -> log.info("Debit card saved: {}", result),
                error -> log.error("Error saving debit card: {}", error.getMessage())
        );
    }
}
