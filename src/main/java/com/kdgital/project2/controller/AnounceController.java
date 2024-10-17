package com.kdgital.project2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/announce")
@Slf4j
@RequiredArgsConstructor
public class AnounceController {
	
	@GetMapping("/announceList")
	public String join() {
		
		return "announce/announceList";
	}

}
