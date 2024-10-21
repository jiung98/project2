package com.kdgital.project2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.BoardEntity;
import com.kdgital.project2.entity.CsEntity;

public interface CsRepository extends JpaRepository<CsEntity, Long> {
	// 3) 페이징 + 검색 기능
	Page<CsEntity> findByCsTitleContains(String searchWord, PageRequest of);
	Page<CsEntity> findByCsWriterContains(String searchWord, PageRequest of);
	Page<CsEntity> findByCsContentContains(String searchWord, PageRequest of);
	
	
	// 2) 검색기능을 위해 추가
	/*
	List<BoardEntity> findByBoardTitleContains(String searchWord, Sort sort);
	List<BoardEntity> findByBoardWriterContains(String searchWord, Sort sort);
	List<BoardEntity> findByBoardContentContains(String searchWord, Sort sort);
	*/
}
