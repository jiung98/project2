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

import com.kdgital.project2.dto.AnnounceDTO;
import com.kdgital.project2.dto.LoginUserDetails;
import com.kdgital.project2.service.AnnounceService;
import com.kdigital.poject2.util.PageNavigator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/announce")
@RequiredArgsConstructor
public class AnnounceController {
	
	final AnnounceService announceService;

	// 한 페이지의 게시글 수
	@Value("${user.board.pageLimit}")
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
	@GetMapping("/announceList")
	public String boardList(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @PageableDefault(page=1) Pageable pageable
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model) {

		// 검색기능 + 페이징
		Page<AnnounceDTO> list = announceService.selectAll(pageable, searchItem, searchWord); 


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
		return "announce/announceList";
	}

	/**
	 * 글쓰기 화면 요청
	 * @return
	 */
	@GetMapping("/announceWrite")
	public String boardWriter(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model
			) {

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		return "announce/announceWrite";
	}

	/**
	 * DB에 글을 등록 처리하는 요청
	 * 첨부 파일도 포함
	 * @return
	 */
	@PostMapping("/announceWrite")
	public String boardWriter(@ModelAttribute AnnounceDTO announceDTO) {
		log.info("클라이언트에서 전송된 데이터 : {}", announceDTO.toString());

		announceService.insertBoard(announceDTO);

		return "redirect:/announce/announceList";
	}

	/**
	 * 글 자세히 보기
	 * 검색 후의 정보를 전달받도록 함 
	 * @param boardNum
	 * @param model
	 * @return
	 */
	@GetMapping("/announceDetail")
	public String boardDetail(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @RequestParam(name="boardNum") Long serno
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model) {

		AnnounceDTO announce = announceService.selectOne(serno);
		System.out.println(serno);
		// 조회수 증가
		announceService.incrementHitcount(serno);

		if(announce == null) {
			return "redirect:/announce/announceList"; 
		}

		model.addAttribute("announce", announce);
		// 검색 기능이 추가되면 계속 달고 다녀야 함
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);

		// 로그인이 된 사용자의 경우 로그인 아이디를 가져감
		if(loginUser != null) {
			model.addAttribute("loginName", loginUser.getUserName());  // 실명
		}

		return "announce/announceDetail"; 
	}

	/**
	 * 전달받은 글번호(boardNum)을 받아 service로 전달
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/announceDelete")
	public String boardDelete(
			@RequestParam(name="boardNum") Long serno
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, RedirectAttributes rttr
			) {

		announceService.deleteOne(serno);

		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);

		return "redirect:/announce/announceList";
	}

	/**
	 * 게시글 수정을 위해 화면에 출력할 내용을 조회
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/announceUpdate")
	public String boardUpdate(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @RequestParam(name="boardNum") Long serno
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model
			) {


		AnnounceDTO announce = announceService.selectOne(serno); // 조회

		model.addAttribute("announce", announce);

		// 검색 기능이 추가되면 계속 달고 다녀야 함
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		
		return "announce/announceUpdate";
	}


//	/**
//	 * 전달받은 게시글 번호에 파일을 다운로드
//	 * @return
//	 */
//	@GetMapping("/download")
//	public String download(
//			@RequestParam(name="boardNum") Long boardNum
//			, HttpServletResponse response
//			) {
//
//		BoardDTO boardDTO = boardService.selectOne(boardNum);
//
//		String originalFileName= boardDTO.getOriginalFileName(); 
//		String savedFileName= boardDTO.getSavedFileName(); 
//
//		log.info("원본 파일명 : {}", originalFileName);
//		log.info("저장 파일명 : {}", savedFileName);
//		log.info("저장 디렉토리 : {}", uploadPath);
//
//		try {
//			// 파일명이 영어가 아닌 경우 사용자 입장에서는 파일명이 깨지지 않도록 해야하므로 필요
//			String tempName = URLEncoder.encode(
//					originalFileName, 
//					StandardCharsets.UTF_8.toString());
//
//			response.setHeader("Content-Disposition", "attachment;filename="+tempName);
//
//		} catch (UnsupportedEncodingException e) {
//
//			e.printStackTrace();
//		}
//
//		String fullPath = uploadPath + "/" + savedFileName;
//
//		FileInputStream filein = null;
//		ServletOutputStream fileout = null;
//
//		try {
//			filein = new FileInputStream(fullPath);
//			fileout = response.getOutputStream();
//
//			FileCopyUtils.copy(filein, fileout);
//
//			fileout.close();
//			filein.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

//	/**
//	 * 게시판 수정화면에서 파일만 삭제하도록 요청
//	 */
//	@GetMapping("/deleteFile")
//	public String deleteFile(
//			@RequestParam(name="boardNum") Long boardNum
//			, RedirectAttributes rttr
//			) {
//
//		// boardService에 파일삭제 요청 (Update와 동일)
//		boardService.deleteFile(boardNum);
//
//		rttr.addAttribute("boardNum", boardNum);
//		return "redirect:/board/boardDetail";
//	}
}
