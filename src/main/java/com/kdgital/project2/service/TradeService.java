package com.kdgital.project2.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.kdgital.project2.dto.TradeDTO;
import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.repository.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    public List<TradeDTO> getTradeDataByDate() {
        return tradeRepository.findTradeDataByDate();
    }

    public List<TradeDTO> getImportSumByDate() {
        return tradeRepository.findImportSumByDate();
    }

    public Map<String, List<String>> getDropdownOptions() {
        Map<String, List<String>> options = new HashMap<>();
        options.put("tradeDates", tradeRepository.findDistinctTradeDates());
        options.put("rCodes", tradeRepository.findDistinctRCodes());
        options.put("cCodes", tradeRepository.findDistinctCCodes());
        return options;
    } 

    public List<TradeEntity> getFilteredTradeData(LocalDate tradeDate, String rCode, String cCode) {
        return tradeRepository.findFilteredTradeData(tradeDate, rCode, cCode);
    }
    
    public List<TradeEntity> findByFilters(String tradeDate, String rCode, String cCode) {
        return tradeRepository.findByFilters(tradeDate, rCode, cCode);
    }
}