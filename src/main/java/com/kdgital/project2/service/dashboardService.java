package com.kdgital.project2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.dashboardDTO;
import com.kdgital.project2.entity.RegionEntity;
import com.kdgital.project2.entity.dashboardEntity;
import com.kdgital.project2.repository.dashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class dashboardService {
    private final dashboardRepository repository;
    private final RegionService regionService;  // RegionEntity를 처리하는 서비스

    // 저장 메서드
    public void saveMainService(dashboardDTO dto) {
        RegionEntity regionEntity = regionService.findByCode(dto.getRegionName());
        dashboardEntity entity = dashboardDTO.toEntity(dto, regionEntity);
        repository.save(entity);
    }

    // 연도별 생산량 데이터 조회
    public List<dashboardDTO> getYearlyProductionData() {
        List<dashboardEntity> entities = repository.findAll();
        return entities.stream()
                .map(dashboardEntity::toDTO)
                .collect(Collectors.toList());
    }
}
