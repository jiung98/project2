package com.kdgital.project2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.DemandDTO;
import com.kdgital.project2.entity.DemandEntity;
import com.kdgital.project2.repository.DemandRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;

    // demand_date 기준으로 demand 값을 가져오는 메서드
    public List<DemandDTO> getDemandDataByDate() {
        return demandRepository.findDemandDataByDate();
    }
}
