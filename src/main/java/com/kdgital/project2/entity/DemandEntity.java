package com.kdgital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.dto.DemandDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

@Entity
@Table(name = "demands")
public class DemandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="demand_no")
    private Long demandNo;

    @Column(name="demand_date")
    private LocalDate demandDate;

    @Column(name="usa_yk")
    private BigDecimal usaYk;
    @Column(name="usa_gk")
    private BigDecimal usaGk;
    @Column(name="usa_gs")
    private BigDecimal usaGs;
    @Column(name="jp_yk")
    private BigDecimal jpYk;
    @Column(name="jp_ik")
    private BigDecimal jpIk;
    @Column(name="jp_gk")
    private BigDecimal jpGk;

    @Column(name = "demand", nullable = false)
    private Long demand;

    private static DemandEntity toEntity(DemandDTO demandDTO) {
    	return DemandEntity.builder()
    			.demandNo(demandDTO.getDemandNo())
    			.demandDate(demandDTO.getDemandDate())
    			.usaYk(demandDTO.getUsaYk())
    			.usaGk(demandDTO.getUsaGk())
    			.usaGs(demandDTO.getUsaGs())
    			.jpYk(demandDTO.getJpYk())
    			.jpIk(demandDTO.getJpIk())
    			.jpGk(demandDTO.getJpGk())
    			.demand(demandDTO.getDemand())
    			.build(); 
    }
 
}