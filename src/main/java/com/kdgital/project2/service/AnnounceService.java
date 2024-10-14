package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.AnnounceEntity;
import com.kdgital.project2.repository.AnnounceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnounceService {
	private final AnnounceRepository announce;
	@Transactional
	public void saveAnnounce(AnnounceEntity announceEntity) {
		announce.save(announceEntity);
	}
}
