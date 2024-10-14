package com.kdgital.project2.dto;

import com.kdgital.project2.entity.RegionEntity;

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

public class RegionDTO {
    private String rCode;
    private String region;

    private static RegionDTO toDTO(RegionEntity regionEntity) {
    	return RegionDTO.builder()
    			.rCode(regionEntity.getRCode())
    			.region(regionEntity.getRegion())
    			.build();
    }
}