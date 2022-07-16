package com.example.appcardtransfermoney.service;

import com.example.appcardtransfermoney.entity.Card;
import com.example.appcardtransfermoney.payload.CardDto;
import com.example.appcardtransfermoney.repository.CardRepository;
import com.example.appcardtransfermoney.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> addCard(CardDto cardDto, HttpServletRequest request){
        if (cardRepository.existsByCardNumberAndUsername(cardDto.getNumber(), getUsername(request)))
            return ResponseEntity.ok("Bunday card mavjud!");
        Card card = new Card();
        card.setUsername(getUsername(request));
        card.setCardNumber(cardDto.getNumber());
        card.setExpireDate(new Date(System.currentTimeMillis()+(1000*60*60*24*365*4L)));
        card.setBalance(cardDto.getBalance());
        card.setActive(true);
        cardRepository.save(card);
        return ResponseEntity.ok("Saqlandi");
    }

    public HttpEntity<?> getByUsernameAndId(HttpServletRequest request, Integer id){
        String username = getUsername(request);
        return ResponseEntity.ok(cardRepository.findByUsernameAndId(username, id));
    }
    public void settingsCard(){
        List<Card> cardList = cardRepository.findAll();
        for (Card card : cardList) {
            if (card.getExpireDate().before(new Date())){
                card.setActive(false);
                cardRepository.save(card);
            }
        }

    }
    public  String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        return jwtProvider.getUsernameFromToken(token);
    }
}