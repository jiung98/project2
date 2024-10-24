package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.kdgital.project2.entity.dashboardEntity;

import java.util.List;

public interface dashboardRepository extends JpaRepository<dashboardEntity, Long> {

    // 연도별 생산량 합계를 구하는 쿼리
    @Query("SELECT YEAR(d.mainDate) AS year, SUM(d.production) AS totalProduction " +
           "FROM dashboardEntity d GROUP BY YEAR(d.mainDate)")
    List<Object[]> findYearlyProduction();
}
