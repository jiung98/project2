package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.DemandEntity;

public interface DemandRepository extends JpaRepository<DemandEntity, Long> {

}
