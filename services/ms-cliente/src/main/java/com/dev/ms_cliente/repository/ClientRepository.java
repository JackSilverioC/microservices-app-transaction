package com.dev.ms_cliente.repository;

import com.dev.ms_cliente.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client,String> {
}
