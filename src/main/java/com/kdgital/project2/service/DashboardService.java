package com.kdgital.project2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.DashboardDTO;
import com.kdgital.project2.entity.DashboardEntity;
import com.kdgital.project2.repository.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DashboardRepository dashboardRepository;

//    public List<DashboardDTO> getTemperatureDataByRCode(String rCode) {
//        return dashboardRepository.findTemperatureByRCode(rCode);
//    }
    
    // mainDate 기준으로 production 합계를 가져오는 메서드
    public List<DashboardDTO> getProductionDataByDate() {
        return dashboardRepository.findProductionSumByDate();
    }

    // r_code에 맞는 데이터를 모두 가져오는 메서드
    public Map<String, List<DashboardDTO>> getTemperatureDataByRCode() {
        LocalDate startDate = LocalDate.of(2014, 1, 1);
        List<String> rCodes = List.of("60", "56", "31", "51", "41");
        
        Map<String, List<DashboardDTO>> temperatureDataByRCode = new HashMap<>();
        
        for (String rCode : rCodes) {
            List<DashboardDTO> temperatureData = dashboardRepository.findTemperatureByRCode(rCode, startDate);
            temperatureDataByRCode.put(rCode, temperatureData);
        }

        return temperatureDataByRCode;
    }
}