package com.kdgital.project2.entity;

import java.util.List;

import com.kdgital.project2.dto.CountryDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "countries")
public class CountryEntity {
    @Id
    @Column(name="c_code")
    private String cCode;
    @Column(name = "country", nullable = false)
    private String country;

    private static CountryEntity toEntity(CountryDTO countryDTO) {
    	return CountryEntity.builder()
    			.cCode(countryDTO.getCCode())
    			.country(countryDTO.getCountry())
    			.build(); 
    }
}