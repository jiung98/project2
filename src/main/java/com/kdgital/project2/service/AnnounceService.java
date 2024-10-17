package com.kdgital.project2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.AnnounceDTO;
import com.kdgital.project2.entity.AnnounceEntity;
import com.kdgital.project2.repository.AnnounceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnounceService {
	private final AnnounceRepository announceRepository;

	// 페이징할 때 한 페이지 출력할 글 개수
	@Value("${user.board.pageLimit}")
	private int pageLimit;	

	// 업로드된 파일이 저장될 디렉토리 경로를 읽어옴
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;


	/**
	 * DB에 게시글 저장
	 * @param boardDTO : 저장해야하는 게시글
	 */
	public void insertBoard(AnnounceDTO announceDTO) {
		log.info("저장 경로: {}", uploadPath);

		// 첨부 파일 처리
		String originalFileName = null;
		String savedFileName = null;
		
//		// 파일 첨부여부 확인
//		if(!boardDTO.getUploadFile().isEmpty()) {
//			savedFileName = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
//			originalFileName = boardDTO.getUploadFile().getOriginalFilename();
//
//			boardDTO.setOriginalFileName(originalFileName);
//			boardDTO.setSavedFileName(savedFileName);
//		}
//
//		log.info("원본 파일명: {}", originalFileName);
//		log.info("저장 파일명: {}", savedFileName);

		// 1) Entity로 변환
		AnnounceEntity announceEntity = AnnounceEntity.toEntity(announceDTO);

		// 2) save()로 데이터 저장
		announceRepository.save(announceEntity);
	}
	
	/**
	 * 게시글 목록 요청 (검색기능 추가)
	 * @param pageable 
	 * @param searchWord 
	 * @param searchItem 
	 * @return
	 */
	public Page<AnnounceDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
		int page = pageable.getPageNumber() - 1;
		// -1을 한 이유 : DB page의 위치의 값은 0부터 시작하므로
		// 사용자가 1페이지를 요청하면 DB페이지를 0페이지를 가져와야 한다.

		// List<BoardDTO> list = new ArrayList<>();

		// 3) 페이징이 추가된 조회
		Page<AnnounceEntity> entityList = null;

		switch(searchItem) { 
		case "boardTitle"   :
			entityList = announceRepository.findByBoardTitleContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum") ));
			break;
		case "boardWriter"  :
			entityList = announceRepository.findByBoardWriterContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum") ));
			break;
		case "boardContent" :
			entityList = announceRepository.findByBoardContentContains(
					searchWord, 
					PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum") ));
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

		Page<AnnounceDTO> list = null;

		// 일반 기능만 가지고 있는 형태
		// entityList.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));

		// 페이징 형태의 list로 변환
		// 목록에서 사용할 필요한 데이터만 간추림(생성자 만듦)
		list = entityList.map(
				(announce) -> new AnnounceDTO(
						announce.getSerno(),
						announce.getTitle(),
						announce.getWriter(),
						announce.getHitCount(),
						announce.getCreateDate(),
						announce.getReplyCount()
						)
				);

		return list;
	}
	/**
	 * PK에 해당하는 boardNum 값을 이용해 글 한 개 조회
	 * @param boardNum
	 * @return
	 */
	public AnnounceDTO selectOne(Long serno) {
		Optional<AnnounceEntity> entity = announceRepository.findById(serno);

		// 데이터를 꺼내 BoardDTO로 변환
		if(entity.isPresent()) {
			AnnounceEntity temp = entity.get();
			return AnnounceDTO.toDTO(temp);
		}
		return null;
	}

	/**
	 * 전달받은 글번호(boardNum)을 DB 에서 삭제
	 * @param boardNum
	 */
	@Transactional
	public void deleteOne(Long serno) {
		// 글번호에 해당하는 글이 존재하는지 읽어옴.
		Optional<AnnounceEntity> entity = announceRepository.findById(serno);

//		if(entity.isPresent()) {
//			AnnounceEntity anounce = entity.get();
//			String savedFileName = anounce.getSavedFileName();

//			// 첨부파일이 있으면 파일을 삭제하고, DB에서도 삭제
//			if(savedFileName != null) {
//				String fullPath = uploadPath + "/" + savedFileName;
//				boolean result = FileService.deleteFile(fullPath);
//
//				log.info("파일 삭제 여부 : {}", result); // true이면 삭제됨
//			}
//
//			announceRepository.deleteById(serno);
//		}
	}

//	/**
//	 * DB에 데이터를 수정처리
//	 * 파일이 첨부되었을 경우 
//	 *    1) 저장 작업 - 원래 글에 첨부파일이 없는 경우
//	 *    2) 또는 삭제 후 다른 파일로 수정하는 작업 - 이전에 첨부 파일이 있는 상태에서
//	 *       다른 파일을 첨부 했을 경우
//	 * @param board
//	 * @return
//	 */
//	@Transactional
//	public void updateBoard(AnnounceDTO announce) {
//		MultipartFile uploadFile = announce.getUploadFile();
//
//		String originalFileName = null;	// 새로운 파일이 첨부되었을 때
//		String savedFileName    = null;	// 새로운 파일이 첨부되었을 때
//		String oldSavedFileName = null;	// 기존에 업로드된 파일이 있을 경우 그 파일명
//
//		// 1) 수정하면서 첨부파일을 송신할 경우
//		if(!uploadFile.isEmpty()) {
//			originalFileName = uploadFile.getOriginalFilename();
//			savedFileName    = FileService.saveFile(uploadFile, uploadPath);
//		}
//
//		// 2) 수정하려는 데이터가 있는지 조회
//		Optional<BoardEntity> entity = boardRepository.findById(board.getBoardNum());
//
//		if(entity.isPresent()) {
//			BoardEntity temp = entity.get();
//			oldSavedFileName = temp.getSavedFileName();
//
//			// 기존파일이 있고, 수정하면서 첨부파일이 있을 때
//			if(oldSavedFileName != null && !uploadFile.isEmpty()) {
//				// 예전 파일은 삭제
//				String fullPath = uploadPath + "/" + oldSavedFileName; 
//				FileService.deleteFile(fullPath);
//
//				// 파일명 바꾸기
//				temp.setOriginalFileName(originalFileName);
//				temp.setSavedFileName(savedFileName);
//			}
//
//			// 기존 파일은 없고, 수정하면서 첨부파일이 있을 때
//			if(oldSavedFileName == null && !uploadFile.isEmpty()) {
//				// 파일 삭제는 없이 파일명 세팅
//				temp.setOriginalFileName(originalFileName);
//				temp.setSavedFileName(savedFileName);
//			}
//
//			// 기존 첨부파일이 없을 때, 수정하면서 파일 첨부도 안하면
//			// 널값을 저장하면 됨! 
//
//			// 
//			temp.setBoardTitle(board.getBoardTitle());   
//			temp.setBoardContent(board.getBoardContent());
//			
//			boardRepository.save(temp);
//		}
//	}

	/**
	 * 조회수 증가
	 * @param boardNum
	 */
	@Transactional
	public void incrementHitcount(Long serno) {
		Optional<AnnounceEntity> entity = announceRepository.findById(serno);

		if(entity.isPresent()) {
			AnnounceEntity temp = entity.get();
			temp.setHitCount(temp.getHitCount() + 1);
		}
	}
//	/**
//	 * 게시글은 그대로, 파일만 삭제
//	 * @param boardNum : 파일이 저장된 게시글번호
//	 */
//	@Transactional
//	public void deleteFile(Long boardNum) {
//		// 1) 데이터 조회
//		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
//
//		String oldSavedFileName = null;
//
//		if(entity.isPresent()) {
//			BoardEntity temp = entity.get();
//			oldSavedFileName = temp.getSavedFileName();
//
//			// 2) 예전 파일은 삭제
//			String fullPath = uploadPath + "/" + oldSavedFileName; 
//			FileService.deleteFile(fullPath);
//
//			// 3) 파일명을 null로
//			temp.setOriginalFileName(null);
//			temp.setSavedFileName(null);
//		}
//	}
}
