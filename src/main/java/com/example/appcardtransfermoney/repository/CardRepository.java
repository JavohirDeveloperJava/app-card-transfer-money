package com.example.appcardtransfermoney.repository;

import com.example.appcardtransfermoney.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.Optional;
@EnableJpaRepositories

public interface CardRepository extends JpaRepository<Card,Integer> {

//    Boolean existsByNumberAndUsername(String number, String username);
    Boolean existsByCardNumberAndUsername(String cardNumber, String username);

//    Optional<Card> findByUsername(String username);

    Optional<Card> findByUsername(String username);

    Card findByUsernameAndId(String username, Integer id);

    Optional<Card> findByUsernameAndCardNumberAndActiveTrue(String username, String cardNumber);

    Optional<Card> findByCardNumberAndActiveTrue(String cardNumber);

    Optional<Card> findByUsernameAndActiveTrue(String username);
}
