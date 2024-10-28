package com.kdgital.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdgital.project2.dto.DemandDTO;
import com.kdgital.project2.entity.DemandEntity;

public interface DemandRepository extends JpaRepository<DemandEntity, Long> {
	// demand_date를 기준으로 demand 값을 오름차순으로 가져오는 쿼리
    @Query("SELECT new com.kdgital.project2.dto.DemandDTO(e.demandDate, e.demand) " +
           "FROM DemandEntity e " +
           "ORDER BY e.demandDate ASC")
    List<DemandDTO> findDemandDataByDate();
 // 수요량 데이터를 날짜별로 정렬하여 가져오는 쿼리
    @Query("SELECT d FROM DemandEntity d ORDER BY d.demandDate ASC")
    List<DemandEntity> findAllOrderedByDate();
}
