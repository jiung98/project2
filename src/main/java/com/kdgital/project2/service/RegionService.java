package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.RegionEntity;
import com.kdgital.project2.repository.RegionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
	private final RegionRepository region;
	@Transactional
	public void saveAnnounce(RegionEntity regionEntity) {
		region.save(regionEntity);
	}
}
