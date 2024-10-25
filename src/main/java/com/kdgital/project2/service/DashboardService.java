package com.kdgital.project2.service;

import java.util.List;
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


    // mainDate 기준으로 production 합계를 가져오는 메서드
    public List<DashboardDTO> getProductionDataByDate() {
        return dashboardRepository.findProductionSumByDate();
    }
    
}