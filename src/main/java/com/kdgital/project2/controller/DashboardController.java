package com.kdgital.project2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/dashboard")
@Slf4j
@RequiredArgsConstructor
public class DashboardController {
	/**
	 * about us을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/dashboard")
	public String join() {

		return "dashboard/dashboard";
	}

}