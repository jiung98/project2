package com.kdgital.project2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdgital.project2.dto.dashboardDTO;
import com.kdgital.project2.service.dashboardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dashboard")
@Slf4j
@RequiredArgsConstructor
public class dashboardController {
    private final dashboardService service;

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        List<dashboardDTO> productionData = service.getYearlyProductionData();
        model.addAttribute("productionData", productionData);  // 생산량 데이터를 모델에 추가
        return "dashboard/dashboard";
    }
}
