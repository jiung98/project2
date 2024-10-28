package com.kdgital.project2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.DemandDTO;
import com.kdgital.project2.entity.DemandEntity;
import com.kdgital.project2.repository.DemandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;

    // demand_date 기준으로 demand 값을 가져오는 메서드
    public List<DemandDTO> getDemandDataByDate() {
        return demandRepository.findDemandDataByDate();
    }
    public List<DemandDTO> getDetailedDemandData() {
        // DemandRepository에서 정렬된 데이터를 가져와서 DTO로 변환하여 반환
        return demandRepository.findAllOrderedByDate().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // DemandEntity를 DemandDTO로 변환하는 메서드
    private DemandDTO convertToDTO(DemandEntity entity) {
        DemandDTO dto = new DemandDTO();
        dto.setDemandDate(entity.getDemandDate());
        dto.setUsaYk(entity.getUsaYk());
        dto.setUsaGk(entity.getUsaGk());
        dto.setUsaGs(entity.getUsaGs());
        dto.setJpYk(entity.getJpYk());
        dto.setJpIk(entity.getJpIk());
        dto.setJpGk(entity.getJpGk());
        return dto;
    }
}
