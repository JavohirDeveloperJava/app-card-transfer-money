package com.example.appcardtransfermoney.service;

import com.example.appcardtransfermoney.entity.Card;
import com.example.appcardtransfermoney.entity.Outcome;
import com.example.appcardtransfermoney.repository.CardRepository;
import com.example.appcardtransfermoney.repository.OutcomeRepository;
import com.example.appcardtransfermoney.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> get(HttpServletRequest request){
        String username = getUsername(request);
        Optional<Card> optionalCard = cardRepository.findByUsernameAndActiveTrue(username);
        if (!optionalCard.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Card card = optionalCard.get();
        Integer id = card.getId();
        List<Outcome> optionalOutcome = outcomeRepository.findAllById(id);
        return ResponseEntity.ok(optionalOutcome);

    }

    public HttpEntity<?> getById(String cardNumber){
        Optional<Card> byCardNumberAndActiveTrue = cardRepository.findByCardNumberAndActiveTrue(cardNumber);
        if (!byCardNumberAndActiveTrue.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Outcome> byFromCard = outcomeRepository.findByFromCard(byCardNumberAndActiveTrue.get());
        if (!byFromCard.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Outcome outcome = byFromCard.get();
        return ResponseEntity.ok(outcome);

    }
    public  String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }
}