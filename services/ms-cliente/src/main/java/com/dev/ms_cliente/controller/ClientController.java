package com.dev.ms_cliente.controller;

import com.dev.ms_cliente.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public Flux<ClientResponseDto> findAll(){
        return clientService.findAll();
    }

    @GetMapping("/{client-id}")
    public Mono<ClientResponseDto> findById(
            @PathVariable("client-id") String clientId
    ){
        return clientService.findById(clientId);
    }

    @PostMapping
    public Mono<ClientResponseDto> createClient(
            @RequestBody ClientRequestDto requestDto
    ){
        return clientService.createClient(requestDto);
    }

    @PutMapping("/{client-id}")
    public Mono<ClientResponseDto> updateClient(
            @PathVariable("client-id") String clientId,
            @RequestBody ClientRequestDto requestDto
    ){
        return clientService.updateClient(clientId, requestDto);
    }

    @DeleteMapping("/{client-id}")
    public Mono<Void> deleteClient(@PathVariable("client-id") String clientId){
        return clientService.deleteClient(clientId);
    }




}
