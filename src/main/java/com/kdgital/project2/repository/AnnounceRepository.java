package com.kdgital.project2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.AnnounceEntity;
import com.kdgital.project2.entity.CountryEntity;

public interface AnnounceRepository extends JpaRepository<AnnounceEntity, Long>{
	// 3) 페이징 + 검색 기능
	Page<AnnounceEntity> findByBoardTitleContains(String searchWord, PageRequest of);
	Page<AnnounceEntity> findByBoardWriterContains(String searchWord, PageRequest of);
	Page<AnnounceEntity> findByBoardContentContains(String searchWord, PageRequest of);

}
