package com.kdgital.project2.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgital.project2.dto.UserDTO;
import com.kdgital.project2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	final UserService memberService;
	
	/**
	 * 회원 가입을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	/**
	 * 회원가입 처리
	 * @return
	 */
	@PostMapping("/join")
	public String join(
		@ModelAttribute UserDTO memberDTO
			) {
		log.info("UserDTO: {}", memberDTO.toString());
		
		memberDTO.setRoles("ROLE_USER");
		memberDTO.setEnabled(true);
		
		boolean result = memberService.join(memberDTO);
		log.info("회원가입 성공: {}", result);
		 
		return "redirect:/";   
	}
	
	/**
	 * 회원 가입 시 사용가능한 아이디인지 비동기를 이용해 처리
	 * @param userId
	 * @return
	 */
	@PostMapping("/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name="userId") String userId) {
		log.info("회원 가입 아이디: {}", userId);
		
		return !memberService.existId(userId);  // 존재하면 true, 아이디가 없으면 false
	}
	
	/**
	 * 로그인 실패 시 처리 화면
	 * @param error
	 * @param errMessage
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(
			@RequestParam(value="error", required=false) String error 
			, @RequestParam(value="errMessage", required=false) String errMessage 
			, Model model
			) {
		
		System.out.println(error);
		System.out.println(errMessage);
		
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);
		
		return "user/login";
	}
	
	/**
	 * 개인정보 수정을 위한 화면 요청
	 * 비밀번호를 한 번 더 입력하는 페이지로 Forwarding 
	 * @return
	 */
	@GetMapping("/mypage")
	public String mypage() {
		return "user/pwdCheck";
	}
	
	/**
	 * 개인정보 수정을 위해 아이디와 비밀번호 확인처리 요청
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	@PostMapping("/pwdCheck")
	public String pwdCheck(
			@RequestParam(name="userId") String userId
			, @RequestParam(name="userPwd") String userPwd
			, Model model
			) {
		
		log.info("id: {}, pwd: {}", userId, userPwd);
		
		UserDTO memberDTO = memberService.pwdCheck(userId, userPwd);
		
		// 개인정보 수정 화면으로 이동
		if(memberDTO != null) {	
			model.addAttribute("userDTO", memberDTO);
			
			return "user/myInfoUpdate";
		} 
		
		return "redirect:/";
	}
	
	/**
	 * 수정처리 요청 
	 * @return
	 */
	@PostMapping("/update")
	public String update(
			@ModelAttribute UserDTO memberDTO
			) {
		
		log.info("===== {}", memberDTO.toString());
		
		boolean result = memberService.update(memberDTO);
		
		if(result) {
			return "redirect:/user/logout";  	// 개인정보가 수정되면 로그아웃 실시
		}
		
		return "redirect:/";  
	}
	

    @GetMapping("/admin")
    public String adminPage() {
        // ROLE_ADMIN 권한을 가진 사용자만 해당 페이지에 접근할 수 있도록 체크
        try {
			SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
			        .filter(a -> a.getAuthority().equals("ROLE_ADMIN"))
			        .findFirst()
			        .orElseThrow(() -> new AccessDeniedException("Access denied"));
		} catch (AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 권한 체크를 통과한 경우에만 해당 페이지로 이동
        return "admin/admin";
    }

}





