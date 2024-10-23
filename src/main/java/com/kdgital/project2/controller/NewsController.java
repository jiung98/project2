package com.kdgital.project2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdgital.project2.dto.BoardDTO;
import com.kdgital.project2.dto.LoginUserDetails;
import com.kdgital.project2.dto.NewsDTO;
import com.kdgital.project2.service.NewsService;
import com.kdgital.project2.util.PageNavigator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/news")
@Slf4j
@RequiredArgsConstructor
public class NewsController {
	
	final NewsService newsService;
	
	// 한 페이지의 게시글 수
		@Value("${user.newsListMini.pageLimit}")
		private int pageLimit;	
	
	@GetMapping("newsList")
	public String join() {
		
		return "news/newsList";
	}
	@GetMapping("newsListMini")
	public String newsList(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, @PageableDefault(page=1) Pageable pageable
			, @RequestParam(name="searchItem", defaultValue="Title") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, Model model) {

		// 검색기능 + 페이징
		Page<NewsDTO> list = newsService.selectAll(pageable, searchItem, searchWord); 


		int totalPages = list.getTotalPages();	// DB에서 받아온 정보에서 추출
		int page = pageable.getPageNumber();	// 사용자가 요청한 정보에서 추출

		PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);

		// 검색기능이 가능
		// List<BoardDTO> list = boardService.selectAll(searchItem, searchWord); 
		List<NewsDTO> newsList = newsService.getAllNews();
		
		model.addAttribute("newsList", newsList);
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("navi", navi);

		// 인증을 받은 사용자
		if(loginUser != null) {
			model.addAttribute("loginName",loginUser.getUserName());
		}
		return "news/newsListMini";
	}

//	 // 전체 뉴스 목록을 화면에 보여주는 메서드
//    @GetMapping("/newsListMini")
//    public String showNewsList(Model model) {
//        List<NewsDTO> newsList = newsService.getAllNews();
//        model.addAttribute("newsList", newsList);
//        return "news/newsListMini";  // Thymeleaf 템플릿 이름
//    }
}
