package com.kdgital.project2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.MainServiceEntity;

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

public class MainServiceDTO {
    private Long mainNo;
    private LocalDate mainDate;
    private String rCode;
    private Long production;
    private BigDecimal temperature;

    private static MainServiceDTO toDTO(MainServiceEntity mainServiceEntity, String rCode) {
    	return MainServiceDTO.builder()
    			.mainNo(mainServiceEntity.getMainNo())
				.mainDate(mainServiceEntity.getMainDate())
				.production(mainServiceEntity.getProduction())
				.temperature(mainServiceEntity.getTemperature())
				.rCode(rCode) 
				.build();
    }
}