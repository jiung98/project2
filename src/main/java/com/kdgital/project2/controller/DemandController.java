package com.kdgital.project2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdgital.project2.dto.DemandDTO;
import com.kdgital.project2.service.DemandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemandController {

    private final DemandService demandService;

    // demand_date 기준으로 demand 데이터를 반환하는 엔드포인트
    @GetMapping("/demand-data")
    public List<DemandDTO> getDemandData() {
        return demandService.getDemandDataByDate();
    }
    @GetMapping("/demand-details")
    public List<DemandDTO> getDemandDetails() {
        return demandService.getDetailedDemandData();
    }
}