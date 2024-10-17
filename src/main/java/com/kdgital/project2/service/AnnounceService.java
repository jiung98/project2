package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.repository.AnnounceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnounceService {
	private final AnnounceRepository announce;
}
