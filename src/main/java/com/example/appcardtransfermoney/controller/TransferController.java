package com.example.appcardtransfermoney.controller;

import com.example.appcardtransfermoney.payload.TransferDto;
import com.example.appcardtransfermoney.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping()
    public HttpEntity<?> transferMoney(HttpServletRequest request, @RequestBody TransferDto transferDto){
        HttpEntity<?> transfer = transferService.transfer(transferDto, request);
        return ResponseEntity.ok(transfer);
    }
}
