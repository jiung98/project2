package com.kdgital.project2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kdgital.project2.dto.BoardDTO;
import com.kdgital.project2.dto.NewsDTO;
import com.kdgital.project2.service.BoardService;
import com.kdgital.project2.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final NewsService newsService;
	private final BoardService boardService;

    @GetMapping({"","/"})
    public String mainPage(Model model) {
        // 뉴스 데이터를 가져와서 모델에 추가
        List<NewsDTO> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        
        List<BoardDTO> boardList = boardService.getAllBoard();
        model.addAttribute("boardList", boardList);

        // 메인 페이지로 이동
        return "main/main";
    }
}
	
