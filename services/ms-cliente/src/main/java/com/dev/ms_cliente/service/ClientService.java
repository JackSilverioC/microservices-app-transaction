package com.dev.ms_cliente.service;

import com.dev.ms_cliente.controller.ClientRequestDto;
import com.dev.ms_cliente.controller.ClientResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<ClientResponseDto> findAll();
    Mono<ClientResponseDto> findById(String id);
    Mono<ClientResponseDto> createClient(ClientRequestDto requestDto);
    Mono<ClientResponseDto> updateClient(String id, ClientRequestDto requestDto);
    Mono<Void> deleteClient(String id);
}
