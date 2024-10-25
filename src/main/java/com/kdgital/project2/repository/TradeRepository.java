package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.dto.TradeDTO;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

    // trade_date를 기준으로 export 값의 합계를 구하는 쿼리
    @Query("SELECT new com.kdgital.project2.dto.TradeDTO(e.tradeDate, SUM(e.export)) " +
           "FROM TradeEntity e " +
           "GROUP BY e.tradeDate " +
           "ORDER BY e.tradeDate ASC")
    List<TradeDTO> findTradeDataByDate();
    
    // trade_date를 기준으로 import 값의 합계를 구하는 쿼리
    @Query("SELECT new com.kdgital.project2.dto.TradeDTO(e.tradeDate, SUM(e.importValue), true) " +
            "FROM TradeEntity e " +
            "GROUP BY e.tradeDate " +
            "ORDER BY e.tradeDate ASC")
     List<TradeDTO> findImportSumByDate();
}