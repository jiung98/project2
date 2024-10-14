package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.CountryEntity;
import com.kdgital.project2.repository.CountryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
	private final CountryRepository country;
	@Transactional
	public void savecountry(CountryEntity countryEntity) {
		country.save(countryEntity);
	}
}
