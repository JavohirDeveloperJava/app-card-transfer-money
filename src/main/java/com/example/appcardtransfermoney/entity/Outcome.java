package com.example.appcardtransfermoney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    private Card fromCard;

    @OneToOne(optional = false)
    private Card toCard;

    @Column(nullable = false)
    private Double amount;

    private Timestamp date = Timestamp.valueOf(LocalDateTime.now());

    private Double commisionAmount;
}
