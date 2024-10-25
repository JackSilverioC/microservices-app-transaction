package com.dev.ms_debitcard.service;

import com.dev.ms_debitcard.model.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientMsClient {

    @Value("${client.service.uri}")
    private String msClientUri;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(msClientUri)
                .build();
    }

    public Mono<Client> callForClient(String id){
        return webClient().get().uri("/{client-id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class);
    }
}
