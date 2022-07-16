package com.example.appcardtransfermoney.payload;

import lombok.Data;

@Data
public class TransferDto {
    private String fromCard;
    private String toCard;
    private Double amount;
}
