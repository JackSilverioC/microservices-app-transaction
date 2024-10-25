package com.dev.ms_cliente.service;

import com.dev.ms_cliente.controller.ClientRequestDto;
import com.dev.ms_cliente.controller.ClientResponseDto;
import com.dev.ms_cliente.mapper.ClientMapper;
import com.dev.ms_cliente.model.Client;
import com.dev.ms_cliente.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Flux<ClientResponseDto> findAll() {
        return clientRepository.findAll()
                .map(clientMapper::fromClientToClientResponseDto);
    }

    @Override
    public Mono<ClientResponseDto> findById(String id) {
        return clientRepository.findById(id)
                .map(clientMapper::fromClientToClientResponseDto);
    }

    @Override
    public Mono<ClientResponseDto> createClient(ClientRequestDto requestDto) {
        var client = clientMapper.fromClientRequestDtoToClient(requestDto);
        return clientRepository.save(client)
                .map(clientMapper::fromClientToClientResponseDto);
    }

    @Override
    public Mono<ClientResponseDto> updateClient(String id, ClientRequestDto requestDto) {
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Client with ID:"+id+"not found")))
                .flatMap(client -> {
                    mergerClient(client,requestDto);
                    return clientRepository.save(client);
                })
                .map(clientMapper::fromClientToClientResponseDto);
    }

    public void mergerClient(Client client, ClientRequestDto requestDto){
        if (requestDto.name()!=null && !requestDto.name().isBlank()){
            client.setName(requestDto.name());
        }
        if (requestDto.age()!=null && !requestDto.age().toString().isBlank()){
            client.setAge(requestDto.age());
        }
    }

    @Override
    public Mono<Void> deleteClient(String id) {
        return clientRepository.deleteById(id);
    }
}
