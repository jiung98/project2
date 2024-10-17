package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.AnnounceEntity;

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

public class AnnounceDTO { 
    private Long serno;
    private String title;
    private String content;
    private int hitCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private int replyCount;
	private String writer;
	
	// 생성자 ==> 페이징을 처리를 위해 BoardService.java에서 Page형태로 받은 데이터중   ~!@~!@~!#~!@$#@~!걸러서 보여줄려고 생성자 따로 만들기~!@#!@$~!@$!@~
	// 목록에 출력할 멤버만 간추리기 위해 만든 생성자
	public AnnounceDTO(Long serno
			, String title
			, String writer
			, int hitCount
			, LocalDateTime createDate
			) {
		this.serno    = serno;
		this.title  = title;
		this.writer = writer;
		this.hitCount    = hitCount;
		this.createDate  = createDate;
	}

    public static AnnounceDTO toDTO(AnnounceEntity announceEntity) {
    	return AnnounceDTO.builder()
    			.serno(announceEntity.getSerno())
    			.title(announceEntity.getTitle())
    			.content(announceEntity.getContent())
    			.createDate(announceEntity.getCreateDate())
    			.updateDate(announceEntity.getUpdateDate())
    			.writer(announceEntity.getWriter())
    			.hitCount(announceEntity.getHitCount())
				.createDate(announceEntity.getCreateDate())
				.updateDate(announceEntity.getUpdateDate())
				.build();
    			
    }
}
