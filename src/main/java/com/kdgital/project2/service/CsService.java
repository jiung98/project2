package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.CsEntity;
import com.kdgital.project2.repository.CsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsService {
	private final CsRepository cs;
	@Transactional
	public void saveCs(CsEntity csEntity) {
		cs.save(csEntity);
	}
}
