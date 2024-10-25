package com.kdgital.project2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgital.project2.dto.DashboardDTO;
import com.kdgital.project2.service.DashboardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {

    private final DashboardService dashboardService;

    // 그룹화된 데이터를 반환하는 엔드포인트
    @GetMapping("/production-data")
    public List<DashboardDTO> getProductionData() {
        log.info("Fetching production sum by date...");
        return dashboardService.getProductionDataByDate();
    }
    
}