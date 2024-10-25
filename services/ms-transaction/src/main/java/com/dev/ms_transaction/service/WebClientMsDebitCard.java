package com.dev.ms_transaction.service;

import com.dev.ms_transaction.model.DebitCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientMsDebitCard {

    @Value("${debitcard.service.uri}")
    private String msClientUri;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(msClientUri)
                .build();
    }

    public Mono<DebitCard> callForDebitCard(String cardNumber){
        return webClient().get().uri("/cardnumber/{card-number}", cardNumber)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DebitCard.class);
    }
}
