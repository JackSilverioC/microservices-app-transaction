package com.dev.ms_transaction.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, TransferenceConfirmation> kafkaTemplate;

    public void updateDebitCardBalance(TransferenceConfirmation transferenceConfirmation){
        log.info("Sending transference confirmation with body <{}>", transferenceConfirmation);
        Message<TransferenceConfirmation> message = MessageBuilder
                .withPayload(transferenceConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "transaction-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
