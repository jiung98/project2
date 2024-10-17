package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.DemandEntity;
import com.kdgital.project2.repository.DemandRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandService {
	private final DemandRepository demand;
	@Transactional
	public void saveDemand(DemandEntity demandEntity) {
		demand.save(demandEntity);
	}
}
