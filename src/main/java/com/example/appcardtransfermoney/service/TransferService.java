package com.example.appcardtransfermoney.service;

import com.example.appcardtransfermoney.entity.Card;
import com.example.appcardtransfermoney.entity.Income;
import com.example.appcardtransfermoney.entity.Outcome;
import com.example.appcardtransfermoney.payload.TransferDto;
import com.example.appcardtransfermoney.repository.CardRepository;
import com.example.appcardtransfermoney.repository.IncomeRepository;
import com.example.appcardtransfermoney.repository.OutcomeRepository;
import com.example.appcardtransfermoney.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    JwtProvider jwtProvider;
    static final Double COMMISSION = 0.01;
    public HttpEntity<?> transfer(TransferDto transferDto, HttpServletRequest request){
        String username = getUsername(request);
        Optional<Card> optionalFromCard =
                cardRepository.findByUsernameAndCardNumberAndActiveTrue(username, transferDto.getFromCard());
        if (!optionalFromCard.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Card fromCard = optionalFromCard.get();

        Optional<Card> optionalToCard = cardRepository.findByCardNumberAndActiveTrue(transferDto.getToCard());
        if (!optionalToCard.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Card toCard = optionalToCard.get();

        Double amount = transferDto.getAmount();
        if (fromCard.getBalance()<amount*(COMMISSION+1))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        fromCard.setBalance(fromCard.getBalance()-amount*(COMMISSION+1));
        toCard.setBalance(toCard.getBalance()+amount);

        Income income = new Income();
        income.setFromCard(fromCard);
        income.setToCard(toCard);
        income.setAmount(amount);
        incomeRepository.save(income);

        Outcome outcome = new Outcome();
        outcome.setFromCard(fromCard);
        outcome.setToCard(toCard);
        outcome.setAmount(amount);
        outcome.setCommisionAmount(amount*(COMMISSION+1));
        outcomeRepository.save(outcome);


        cardRepository.save(fromCard);
        cardRepository.save(toCard);
        return ResponseEntity.ok("Transfer successfly");
    }



    public String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        return jwtProvider.getUsernameFromToken(token);
    }
}
