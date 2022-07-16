package com.example.appcardtransfermoney.repository;

import com.example.appcardtransfermoney.entity.Card;
import com.example.appcardtransfermoney.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {

    List<Outcome> findAllById(Integer id);
    Optional<Outcome> findByFromCard(Card fromCard);
}