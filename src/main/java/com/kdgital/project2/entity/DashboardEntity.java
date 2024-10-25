package com.kdgital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kdgital.project2.dto.DashboardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
@Table(name = "mainservice")
public class DashboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_no")
    private Long mainNo;

    @Column(name = "main_date")
    private LocalDate mainDate;

    @Column(name = "production", nullable = false)
    private Long production;

    @Column(name = "temperatue", nullable = false)
    private BigDecimal temperature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_code")
    private RegionEntity regionEntity;

    // DTO 변환 메소드
    public static DashboardEntity toEntity(DashboardDTO dto, RegionEntity regionEntity) {
        return DashboardEntity.builder()
                .mainNo(dto.getMainNo())
                .mainDate(dto.getMainDate())
                .production(dto.getProduction())
                .temperature(dto.getTemperature())
                .regionEntity(regionEntity)
                .build();
    }
}