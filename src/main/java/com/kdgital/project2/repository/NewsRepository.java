package com.kdgital.project2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.BoardEntity;
import com.kdgital.project2.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

	Page<NewsEntity> findByTitleContains(String searchWord, PageRequest of);
	// 3) 페이징 + 검색 기능

	Page<NewsEntity> findByMediaContains(String searchWord, PageRequest of);
}
