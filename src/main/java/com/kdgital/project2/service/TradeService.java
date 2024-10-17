package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.TradeEntity;
import com.kdgital.project2.repository.TradeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {
	private final TradeRepository trade;
	@Transactional
	public void saveAnnounce(TradeEntity tradeEntity) {
		trade.save(tradeEntity);
	}
}
