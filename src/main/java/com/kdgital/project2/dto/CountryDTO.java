package com.kdgital.project2.dto;



import com.kdgital.project2.entity.CountryEntity;

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
 
public class CountryDTO {
    private String cCode;
    private String country;

    private static CountryDTO toDTO(CountryEntity countryEntity) {
    	return CountryDTO.builder()
    			.cCode(countryEntity.getCCode())
    			.country(countryEntity.getCountry())
    			.build(); 
    }
}