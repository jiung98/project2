package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.CsAnswerEntity;
import com.kdgital.project2.repository.CsAnswerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsAnswerService {
	private final CsAnswerRepository csAnswer;
	@Transactional
	public void saveCsAnswer(CsAnswerEntity csAnswerEntity) {
		csAnswer.save(csAnswerEntity);
	}
}
