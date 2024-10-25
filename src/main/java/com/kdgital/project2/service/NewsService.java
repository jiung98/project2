package com.kdgital.project2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.NewsDTO;
import com.kdgital.project2.entity.NewsEntity;
import com.kdgital.project2.repository.NewsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
	final NewsRepository newsRepository;

	// 페이징할 때 한 페이지 출력할 글 개수
	@Value("${user.newsListMini.pageLimit}")
	private int pageLimit;	

	// 전체 뉴스 목록을 DTO로 변환하여 반환하는 메서드
	public List<NewsDTO> getAllNews() {
		List<NewsEntity> newsList = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "postdate"));
		return newsList.stream()
				.map(news -> new NewsDTO(
						news.getSerno(),
						news.getTitle(),
						news.getMedia(),
						news.getUrl(),
						news.getPostdate()
						))
				.collect(Collectors.toList());
	}

//	public Page<NewsEntity> getPagedNews(int page) {
//		// 페이지 번호는 0부터 시작하며, 10개씩 페이지당 보여주기
//		return newsRepository.findAll(PageRequest.of(page, 10));
//	}
	
	public Page<NewsDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
		int page = pageable.getPageNumber() - 1;
		// -1을 한 이유 : DB page의 위치의 값은 0부터 시작하므로
		// 사용자가 1페이지를 요청하면 DB페이지를 0페이지를 가져와야 한다.

		// List<BoardDTO> list = new ArrayList<>();

		// 3) 페이징이 추가된 조회
		Page<NewsEntity> entityList = null;

		switch(searchItem) {
		case "Title"   :
			entityList = newsRepository.findByTitleContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "postdate") ));
			break;
		case "Media"  :
			entityList = newsRepository.findByMediaContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "postdate") ));
			break;
		case "Url" :
			entityList = newsRepository.findByUrlContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "postdate") ));
			break;
		}

		/* 2) 검색기능이 추가된 조회
			List<BoardEntity> entityList = null;

			switch(searchItem) {
			case "boardTitle"   :
				entityList = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum"));
				break;
			case "boardWriter"  :
				entityList = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum"));
				break;
			case "boardContent" :
				entityList = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum"));
				break;
			}
		 */


		// 1) 1단계 - 단순 조회
		// List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardNum"));

		// Entity를 DTO로 바꾸는 작업 수행

		Page<NewsDTO> list = null;

		// 일반 기능만 가지고 있는 형태
		// entityList.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));

		// 페이징 형태의 list로 변환
		// 목록에서 사용할 필요한 데이터만 간추림(생성자 만듦)
		list = entityList.map(
				(news) -> new NewsDTO(
						news.getSerno(),
						news.getTitle(),
						news.getMedia(),
						news.getUrl(),
						news.getPostdate()
						)
				);


		return list;


	}
}
