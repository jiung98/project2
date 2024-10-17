package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.MainServiceEntity;
import com.kdgital.project2.repository.MainServiceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceService {
	private final MainServiceRepository mainService;
	@Transactional
	public void savaMainService(MainServiceEntity mainServiceEntity) {
		mainService.save(mainServiceEntity);
	}
}
