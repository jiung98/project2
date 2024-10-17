package com.kdgital.project2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.AnnounceEntity;
import com.kdgital.project2.entity.CountryEntity;

public interface AnnounceRepository extends JpaRepository<AnnounceEntity, Long>{
	
}
