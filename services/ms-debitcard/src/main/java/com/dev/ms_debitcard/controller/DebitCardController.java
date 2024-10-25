package com.dev.ms_debitcard.controller;

import com.dev.ms_debitcard.service.DebitCardServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/debitcards")
@RequiredArgsConstructor
public class DebitCardController {

    private final DebitCardServiceImpl debitCardService;

    @GetMapping
    public Flux<DebitCardResponseDto> findAll(){
        return debitCardService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<DebitCardResponseDto> findById(@PathVariable("id") String debitCardId){
        return debitCardService.findById(debitCardId);
    }

    @GetMapping("/cardnumber/{card-number}")
    public Mono<DebitCardResponseDto> findByCardNumber(@PathVariable("card-number") String cardNumber){
        return debitCardService.findByCardNumber(cardNumber);
    }

    @PostMapping
    public Mono<DebitCardResponseDto> createDebitCard(@Valid @RequestBody DebitCardRequestDto requestDto){
        return debitCardService.createDebitCard(requestDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDebitCard(@PathVariable("id") String debitCardId){
        return debitCardService.deleteDebitCard(debitCardId);
    }
}
