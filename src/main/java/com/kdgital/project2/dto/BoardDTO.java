package com.kdgital.project2.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.kdgital.project2.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDTO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private int hitCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	// 업로드 하는 파일을 받는 멤버변수 
	private MultipartFile uploadFile;
	
	private String originalFileName;	// 파일의 원래 파일명
	private String savedFileName;		// 하드디스크에 저장될 때 사용되는 변경된 파일명

	// 생성자 ==> 페이징을 처리를 위해 BoardService.java에서 Page형태로 받은 데이터중   ~!@~!@~!#~!@$#@~!걸러서 보여줄려고 생성자 따로 만들기~!@#!@$~!@$!@~
	// 목록에 출력할 멤버만 간추리기 위해 만든 생성자
	public BoardDTO(Long boardNum
			, String boardTitle
			, String boardWriter
			, int hitCount
			, LocalDateTime createDate
			, String originalFileName
			) {
		this.boardNum    = boardNum;
		this.boardTitle  = boardTitle;
		this.boardWriter = boardWriter;
		this.hitCount    = hitCount;
		this.createDate  = createDate;
		this.originalFileName = originalFileName;
	}
	
	// Entity를 받아서 ----> DTO로 반환 
	public static BoardDTO toDTO(BoardEntity boardEntity) {
		return BoardDTO.builder()
				.boardNum(boardEntity.getBoardNum())
				.boardWriter(boardEntity.getBoardWriter())
				.boardTitle(boardEntity.getBoardTitle())
				.boardContent(boardEntity.getBoardContent())
				.hitCount(boardEntity.getHitCount())
				.createDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.originalFileName(boardEntity.getOriginalFileName())
				.savedFileName(boardEntity.getSavedFileName())
				.build();
	}




}




