package com.kdgital.project2.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kdgital.project2.dto.CsDTO;
import com.kdgital.project2.dto.LoginUserDetails;
import com.kdgital.project2.service.CsService;
import com.kdgital.project2.util.PageNavigator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cs")
@Slf4j
@RequiredArgsConstructor
public class CsController {
	
	final CsService csService;

	// 한 페이지의 게시글 수
	@Value("${user.cs.pageLimit}")
	private int pageLimit;	

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	/**
	 * 게시글 목록 조회를 위해 DB에 요청처리
	 * 1) index에서 넘어올 경우에는 searchItem/searchWord가 없는 상태이므로 기본값 세팅
	 * 2) list에서 직접 입력해서 넘어올 경우 searchItem/searchWord가 있으므로 그 값을 사용
	 * @param model
	 * @return
	 */
	@GetMapping("/csList")
	public String csList(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @PageableDefault(page=1) Pageable pageable
			, @RequestParam(name="searchItem", defaultValue="csTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model) {

		// 검색기능 + 페이징
		Page<CsDTO> list = csService.selectAll(pageable, searchItem, searchWord); 


		int totalPages = list.getTotalPages();	// DB에서 받아온 정보에서 추출
		int page = pageable.getPageNumber();	// 사용자가 요청한 정보에서 추출

		PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);

		// 검색기능이 가능
		// List<BoardDTO> list = boardService.selectAll(searchItem, searchWord); 

		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("navi", navi);

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		return "cs/csList";
	}

	/**
	 * 글쓰기 화면 요청
	 * @return
	 */
	@GetMapping("/csWrite")
	public String csWriter(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model
			) {

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		return "cs/csWrite";
	}

	/**
	 * DB에 글을 등록 처리하는 요청
	 * 첨부 파일도 포함
	 * @return
	 */
	@PostMapping("/csWrite")
	public String csWriter(@ModelAttribute CsDTO csDTO) {
		log.info("클라이언트에서 전송된 데이터 : {}", csDTO.toString());

		csService.insertCs(csDTO);

		return "redirect:/cs/csList";
	}

	/**
	 * 글 자세히 보기
	 * 검색 후의 정보를 전달받도록 함 
	 * @param boardNum
	 * @param model
	 * @return
	 */
	@GetMapping("/csDetail")
	public String csDetail(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @RequestParam(name="csNum") Long csNum
			, @RequestParam(name="searchItem", defaultValue="csTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model) {

		CsDTO cs = csService.selectOne(csNum);
		System.out.println(cs);
		System.out.println(csNum);
		// 조회수 증가
		csService.incrementHitcount(csNum);

		if(cs == null) {
			return "redirect:/cs/csList"; 
		}

		model.addAttribute("cs", cs);
		// 검색 기능이 추가되면 계속 달고 다녀야 함
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);

		// 로그인이 된 사용자의 경우 로그인 아이디를 가져감
		if(loginUser != null) {
			model.addAttribute("loginName", loginUser.getUserName());  // 실명
		}

		return "cs/csDetail"; 
	}

	/**
	 * 전달받은 글번호(boardNum)을 받아 service로 전달
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/csDelete")
	public String csDelete(
			@RequestParam(name="csNum") Long csNum
			, @RequestParam(name="searchItem", defaultValue="csTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, RedirectAttributes rttr
			) {

		csService.deleteOne(csNum);

		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);

		return "redirect:/cs/csList";
	}

	/**
	 * 게시글 수정을 위해 화면에 출력할 내용을 조회
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/csUpdate")
	public String csUpdate(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @RequestParam(name="csNum") Long csNum
			, @RequestParam(name="searchItem", defaultValue="csTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model
			) {

		//System.out.println("asdfasdfasdf");
		
		CsDTO cs = csService.selectOne(csNum); // 조회

		model.addAttribute("cs", cs);

		// 검색 기능이 추가되면 계속 달고 다녀야 함
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		//System.out.println("===================" + cs);
		return "cs/csUpdate";
	}

	/**
	 * 게시글 수정 요청
	 * @param boardNum
	 * @return
	 */
	@PostMapping("/csUpdate")
	public String csUpdate(
			@ModelAttribute CsDTO cs
			, @RequestParam(name="searchItem", defaultValue="csTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, RedirectAttributes rttr
			) {

		log.info("수정할 글: {}", cs.toString());

		csService.updateCs(cs);

		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);

		return "redirect:/cs/csList";
	}
	/**
	 * 전달받은 게시글 번호에 파일을 다운로드
	 * @return
	 */
	@GetMapping("/download")
	public String download(
			@RequestParam(name="csNum") Long csNum
			, HttpServletResponse response
			) {

		CsDTO csDTO = csService.selectOne(csNum);

		String originalFileName= csDTO.getOriginalFileName(); 
		String savedFileName= csDTO.getSavedFileName(); 

		log.info("원본 파일명 : {}", originalFileName);
		log.info("저장 파일명 : {}", savedFileName);
		log.info("저장 디렉토리 : {}", uploadPath);

		try {
			// 파일명이 영어가 아닌 경우 사용자 입장에서는 파일명이 깨지지 않도록 해야하므로 필요
			String tempName = URLEncoder.encode(
					originalFileName, 
					StandardCharsets.UTF_8.toString());

			response.setHeader("Content-Disposition", "attachment;filename="+tempName);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		String fullPath = uploadPath + "/" + savedFileName;

		FileInputStream filein = null;
		ServletOutputStream fileout = null;

		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();

			FileCopyUtils.copy(filein, fileout);

			fileout.close();
			filein.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 게시판 수정화면에서 파일만 삭제하도록 요청
	 */
	@GetMapping("/deleteFile")
	public String deleteFile(
			@RequestParam(name="csNum") Long csNum
			, RedirectAttributes rttr
			) {

		// boardService에 파일삭제 요청 (Update와 동일)
		csService.deleteFile(csNum);

		rttr.addAttribute("csNum", csNum);
		return "redirect:/csNum/csDetail";
	}
}





