package com.dev.ms_transaction.config;

import com.dev.ms_transaction.model.TransactionType;
import com.dev.ms_transaction.service.strategy.CashOutStrategy;
import com.dev.ms_transaction.service.strategy.DepositStrategy;
import com.dev.ms_transaction.service.strategy.TransactionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransactionStrategyConfig {

    @Bean
    public Map<TransactionType, TransactionStrategy> transactionStrategies(
            DepositStrategy depositStrategy,
            CashOutStrategy cashOutStrategy,
            TransactionStrategy transferStrategy) {
        Map<TransactionType, TransactionStrategy> strategies = new HashMap<>();
        strategies.put(TransactionType.DEPOSIT, depositStrategy);
        strategies.put(TransactionType.CASH_OUT, cashOutStrategy);
        strategies.put(TransactionType.TRANSFER, transferStrategy);
        return strategies;
    }

}
