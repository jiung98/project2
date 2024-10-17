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
	@Transactional  //두개의 명령어를 하나로 묶어주는거 save find 이렇게 주로 업데이트에서 많이씀 
	public void savecountry(CountryEntity countryEntity) {
		country.save(countryEntity);
	}
}
