package com.kdgital.project2.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kdgital.project2.entity.MemberEntity;
import com.kdgital.project2.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	@GetMapping({"","/"})
	public String index() {
		System.out.println("첫 요청이 도착함");
		return "index";  //  src/main/resources/templetes/index.html
	}
}