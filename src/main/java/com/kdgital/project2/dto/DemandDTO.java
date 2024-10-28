package com.kdgital.project2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.DemandEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class DemandDTO {
    private Long demandNo;
    private LocalDate demandDate;
    private Long usaYk;
    private Long usaGk;
    private Long usaGs;
    private Long jpYk;
    private Long jpIk;
    private Long jpGk;
    private Long demand;

    private static DemandDTO toDTO(DemandEntity demandEntity) {
    	return DemandDTO.builder()
    			.demandNo(demandEntity.getDemandNo())
    			.demandDate(demandEntity.getDemandDate())
    			.usaYk(demandEntity.getUsaYk())
    			.usaGk(demandEntity.getUsaGk())
    			.usaGs(demandEntity.getUsaGs())
    			.jpYk(demandEntity.getJpYk())
    			.jpIk(demandEntity.getJpIk())
    			.jpGk(demandEntity.getJpGk())
    			.demand(demandEntity.getDemand())
    			.build();  
    			
    }
    // 생성자 추가: JPQL 쿼리에서 사용될 생성자
    public DemandDTO(LocalDate demandDate, Long demand) {
        this.demandDate = demandDate;
        this.demand = demand;
    }
 
}