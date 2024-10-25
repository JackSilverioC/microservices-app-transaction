package com.dev.ms_debitcard.repository;

import com.dev.ms_debitcard.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {

    Mono<DebitCard> findByCardNumber(String cardNumber);

}
