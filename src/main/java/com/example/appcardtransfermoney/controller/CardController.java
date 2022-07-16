package com.example.appcardtransfermoney.controller;




import com.example.appcardtransfermoney.payload.CardDto;
import com.example.appcardtransfermoney.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/create")
    public HttpEntity<?> createCard(HttpServletRequest request, @RequestBody CardDto cardDto){
        HttpEntity<?> httpEntity = cardService.addCard(cardDto, request);
        return ResponseEntity.ok(httpEntity);
    }

    @GetMapping(value = "/get/{id}")
    public HttpEntity<?> getByCardId(@PathVariable Integer id, HttpServletRequest request){
        HttpEntity<?> card = cardService.getByUsernameAndId(request, id);
        return ResponseEntity.ok(card);
    }
}