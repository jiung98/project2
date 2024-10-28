package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.dto.TradeDTO;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

    @Query("SELECT new com.kdgital.project2.dto.TradeDTO(e.tradeDate, SUM(e.export)) " +
           "FROM TradeEntity e " +
           "GROUP BY e.tradeDate " +
           "ORDER BY e.tradeDate ASC")
    List<TradeDTO> findTradeDataByDate();
    
    @Query("SELECT new com.kdgital.project2.dto.TradeDTO(e.tradeDate, SUM(e.importValue), true) " +
            "FROM TradeEntity e " +
            "GROUP BY e.tradeDate " +
            "ORDER BY e.tradeDate ASC")
     List<TradeDTO> findImportSumByDate(); 
    
    @Query("SELECT DISTINCT FUNCTION('DATE_FORMAT', t.tradeDate, '%Y-%m-%d') FROM TradeEntity t")
    List<String> findDistinctTradeDates();
    @Query("SELECT DISTINCT t.rCode FROM TradeEntity t ORDER BY t.rCode")
    List<String> findDistinctRCodes();

    @Query("SELECT DISTINCT t.cCode FROM TradeEntity t ORDER BY t.cCode")
    List<String> findDistinctCCodes();

    @Query("SELECT t FROM TradeEntity t WHERE "
           + "(?1 IS NULL OR t.tradeDate = ?1) AND "
           + "(?2 IS NULL OR t.rCode = ?2) AND "
           + "(?3 IS NULL OR t.cCode = ?3)")
    List<TradeEntity> findFilteredTradeData(LocalDate tradeDate, String rCode, String cCode);
    
    @Query("SELECT t FROM TradeEntity t WHERE " +
            "( :tradeDate IS NULL OR t.tradeDate = :tradeDate ) AND " +
            "( :rCode IS NULL OR t.rCode = :rCode ) AND " +
            "( :cCode IS NULL OR t.cCode = :cCode )")
     List<TradeEntity> findByFilters(@Param("tradeDate") String tradeDate, 
                                     @Param("rCode") String rCode, 
                                     @Param("cCode") String cCode);
}