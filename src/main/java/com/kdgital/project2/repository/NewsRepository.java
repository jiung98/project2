package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

}
