package com.kdgital.project2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class AboutController {
	/**
	 * about us을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/about/about")
	public String join() {
		
		return "about/about";
	}
	@GetMapping("/dashboard/dashboard")
	public String joinIn() {
		
		return "dashboard/dashboard";
	}
}
