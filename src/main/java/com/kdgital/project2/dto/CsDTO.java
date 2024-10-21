package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.kdgital.project2.entity.CsEntity;

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

public class CsDTO {
	private Long csNum;
	private String csWriter;
	private String csTitle;
	private String csContent;
	private int hitCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private int replyCount;
	
	// 업로드 하는 파일을 받는 멤버변수 
	private MultipartFile uploadFile;
	
	private String originalFileName;	// 파일의 원래 파일명
	private String savedFileName;		// 하드디스크에 저장될 때 사용되는 변경된 파일명

	// 생성자 ==> 페이징을 처리를 위해 BoardService.java에서 Page형태로 받은 데이터중   ~!@~!@~!#~!@$#@~!걸러서 보여줄려고 생성자 따로 만들기~!@#!@$~!@$!@~
	// 목록에 출력할 멤버만 간추리기 위해 만든 생성자
	public CsDTO(Long csNum
			, String csTitle
			, String csWriter
			, int hitCount
			, LocalDateTime createDate
			, String originalFileName
			, int replyCount) {
		this.csNum    = csNum;
		this.csTitle  = csTitle;
		this.csWriter = csWriter;
		this.hitCount    = hitCount;
		this.createDate  = createDate;
		this.originalFileName = originalFileName;
		this.replyCount  = replyCount;
	}

    public static CsDTO toDTO(CsEntity csEntity) {
    	return CsDTO.builder()
    			.csNum(csEntity.getCsNum())
				.csWriter(csEntity.getCsWriter())
				.csTitle(csEntity.getCsTitle())
				.csContent(csEntity.getCsContent())
				.hitCount(csEntity.getHitCount())
			
				.originalFileName(csEntity.getOriginalFileName())
				.savedFileName(csEntity.getSavedFileName())
//				.createDate(boardDTO.getCreateDate())
//				.updateDate(boardDTO.getUpdateDate())
				.build();
    }
}