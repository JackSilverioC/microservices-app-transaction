package com.dev.ms_transaction.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TransactionTopic {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("transaction-topic")
                .build();
    }
}
