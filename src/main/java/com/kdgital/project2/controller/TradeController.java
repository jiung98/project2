package com.kdgital.project2.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.kdgital.project2.dto.TradeDTO;
import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.service.TradeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/trade-data")
    public List<TradeDTO> getTradeData() {
        return tradeService.getTradeDataByDate();
    }

    @GetMapping("/import-data")
    public List<TradeDTO> getImportData() {
        return tradeService.getImportSumByDate();
    }

    @GetMapping("/trade-options")
    public Map<String, List<String>> getTradeOptions() {
        return tradeService.getDropdownOptions();
    }

    @GetMapping("/trade-filter")
    public List<TradeEntity> getFilteredTradeData(
            @RequestParam(name = "tradeDate", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate,
            @RequestParam(name = "rCode", required = false) String rCode,
            @RequestParam(name = "cCode", required = false) String cCode) {
        return tradeService.getFilteredTradeData(tradeDate, rCode, cCode);
    }
    @GetMapping("/api/trade-filter-advanced")  // 두 번째 메서드에 고유 URL 지정
    public List<TradeEntity> getFilteredTradeDataAdvanced(
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate,
            @RequestParam(required = false) String rCode,
            @RequestParam(required = false) String cCode) {
    	 return tradeService.getFilteredTradeData(tradeDate, rCode, cCode);
    }
}