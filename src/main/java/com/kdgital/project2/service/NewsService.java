package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.NewsEntity;
import com.kdgital.project2.repository.NewsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {
	private final NewsRepository news;
	@Transactional
	public void savenews(NewsEntity newsEntity) {
		news.save(newsEntity);
	}
}
