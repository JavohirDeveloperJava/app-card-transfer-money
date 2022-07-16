package com.example.appcardtransfermoney.repository;

import com.example.appcardtransfermoney.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

//    List<Income> findAllByAndFromCard_Id();
    List<Income> findAllByFromCard_Id(Integer fromCard_id);

//    SELECT * from income WHERE from_card_id=
}
