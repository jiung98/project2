package com.kdgital.project2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.kdgital.project2.dto.TradeDTO;
import com.kdgital.project2.service.TradeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    // trade 데이터를 반환하는 API 엔드포인트
    @GetMapping("/trade-data")
    public List<TradeDTO> getTradeData() {
        return tradeService.getTradeDataByDate();
    }
    // trade_date 기준으로 import 합계를 반환하는 API 엔드포인트
    @GetMapping("/import-data")
    public List<TradeDTO> getImportData() {
        return tradeService.getImportSumByDate();
    }
}