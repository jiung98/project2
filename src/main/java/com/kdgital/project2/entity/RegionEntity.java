package com.kdgital.project2.entity;



import com.kdgital.project2.dto.RegionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "regions")
public class RegionEntity {
    @Id
    @Column(name = "r_code")
    private String rCode;


    @Column(name = "region", nullable = false)
    private String region;

    private static RegionEntity toEntity(RegionDTO regionDTO) {
    	return RegionEntity.builder()
    			.rCode(regionDTO.getRCode())
    			.region(regionDTO.getRegion())
    			.build();
    }
}