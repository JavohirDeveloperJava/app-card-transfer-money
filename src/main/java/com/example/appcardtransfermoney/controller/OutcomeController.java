package com.example.appcardtransfermoney.controller;

import com.example.appcardtransfermoney.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/get")
    public HttpEntity<?> get(HttpServletRequest request){
        return outcomeService.get(request);
    }

    @GetMapping("/get/{cardNumber}")
    public HttpEntity<?> getById(@PathVariable String cardNumber){
        return outcomeService.getById(cardNumber);
    }

}