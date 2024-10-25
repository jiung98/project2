package com.kdgital.project2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kdgital.project2.entity.DashboardEntity;

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
public class DashboardDTO {
    private Long mainNo;
    private LocalDate mainDate;
    private String rCode;
    private Long production;
    private BigDecimal temperature;

    // Entity를 DTO로 변환하는 정적 메소드
    private static DashboardDTO toDTO(DashboardEntity entity, String rCode) {
        return DashboardDTO.builder()
                .mainNo(entity.getMainNo())
                .mainDate(entity.getMainDate())
                .production(entity.getProduction())
                .temperature(entity.getTemperature())
                .rCode(rCode)
                .build();
    }

    // 필요한 생성자 추가
    public DashboardDTO(LocalDate mainDate, Long production) {
        this.mainDate = mainDate;
        this.production = production;
    }
    public DashboardDTO(LocalDate mainDate, BigDecimal temperature) {
        this.mainDate = mainDate;
        this.temperature = temperature;
    }
}