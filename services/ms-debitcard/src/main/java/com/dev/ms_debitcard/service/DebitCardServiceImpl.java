package com.dev.ms_debitcard.service;

import com.dev.ms_debitcard.controller.DebitCardRequestDto;
import com.dev.ms_debitcard.controller.DebitCardResponseDto;
import com.dev.ms_debitcard.mapper.DebitCardMapper;
import com.dev.ms_debitcard.repository.DebitCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DebitCardServiceImpl implements DebitCardService{

    private final DebitCardRepository debitCardRepository;
    private final DebitCardMapper debitCardMapper;
    private final WebClientMsClient webClientMsClient;

    @Override
    public Flux<DebitCardResponseDto> findAll() {
        return debitCardRepository.findAll()
                .map(debitCardMapper::fromDebitCardToDebitCardResponseDto);
    }

    @Override
    public Mono<DebitCardResponseDto> findById(String id){
        return debitCardRepository.findById(id)
                .map(debitCardMapper::fromDebitCardToDebitCardResponseDto);
    }

    @Override
    public Mono<DebitCardResponseDto> findByCardNumber(String cardNumber) {
        return debitCardRepository.findByCardNumber(cardNumber)
                .map(debitCardMapper::fromDebitCardToDebitCardResponseDto);
    }

    @Override
    public Mono<DebitCardResponseDto> createDebitCard(DebitCardRequestDto requestDto) {
        return webClientMsClient.callForClient(requestDto.client().getId())
                        .flatMap(client -> {
                            var debitCard = debitCardMapper.fromDebitCardRequestDtoToDebitCard(requestDto);
                            debitCard.setClient(client);
                            debitCard.setBalance(0.0);
                            return debitCardRepository.save(debitCard);
                        })
                        .map(debitCardMapper::fromDebitCardToDebitCardResponseDto);

    }

    @Override
    public Mono<Void> deleteDebitCard(String id) {
        return debitCardRepository.deleteById(id);
    }



}
