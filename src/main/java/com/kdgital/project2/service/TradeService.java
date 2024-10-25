package com.kdgital.project2.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.kdgital.project2.dto.TradeDTO;
import com.kdgital.project2.repository.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;


    // trade_date 기준으로 export 합계를 가져오는 메서드
    public List<TradeDTO> getTradeDataByDate() {
        return tradeRepository.findTradeDataByDate();
    }
    // trade_date 기준으로 import 합계를 가져오는 메서드
    public List<TradeDTO> getImportSumByDate() {
        return tradeRepository.findImportSumByDate();
    }
}
