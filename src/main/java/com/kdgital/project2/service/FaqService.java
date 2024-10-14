package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.FaqEntity;
import com.kdgital.project2.repository.FaqRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {
	private final FaqRepository faq;
	@Transactional
	public void saveFaq(FaqEntity faqEntity	) {
		faq.save(faqEntity);
	}
}
