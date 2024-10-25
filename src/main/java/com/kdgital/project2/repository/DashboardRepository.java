package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdgital.project2.dto.DashboardDTO;
import com.kdgital.project2.entity.DashboardEntity;
import java.util.List;

public interface DashboardRepository extends JpaRepository<DashboardEntity, Long> {
	 // mainDate를 기준으로 production의 합계를 구하는 쿼리
    @Query("SELECT new com.kdgital.project2.dto.DashboardDTO(e.mainDate, SUM(e.production)) " +
           "FROM DashboardEntity e " +
           "GROUP BY e.mainDate " +
           "ORDER BY e.mainDate ASC")
    List<DashboardDTO> findProductionSumByDate();
    
}