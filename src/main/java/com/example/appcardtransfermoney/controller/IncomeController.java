package com.example.appcardtransfermoney.controller;

import com.example.appcardtransfermoney.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping("/get")
    public HttpEntity<?> getIncome(HttpServletRequest request){
        HttpEntity<?> httpEntity = incomeService.get(request);
        return httpEntity;
    }
}