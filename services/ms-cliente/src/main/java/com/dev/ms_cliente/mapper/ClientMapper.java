package com.dev.ms_cliente.mapper;

import com.dev.ms_cliente.controller.ClientRequestDto;
import com.dev.ms_cliente.controller.ClientResponseDto;
import com.dev.ms_cliente.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientResponseDto fromClientToClientResponseDto(Client client){
        return new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getAge()
        );
    }

    public Client fromClientRequestDtoToClient(ClientRequestDto requestDto){
        return Client.builder()
                .name(requestDto.name())
                .age(requestDto.age())
                .build();
    }

}
