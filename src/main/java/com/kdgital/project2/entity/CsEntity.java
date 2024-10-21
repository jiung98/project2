package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.kdgital.project2.dto.CsDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

@Entity
@Table(name = "cs")
public class CsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cs_num")
	private Long csNum;
	
	@Column(name="cs_writer", nullable = false)
	private String csWriter;
	
	@Column(name="cs_title")
	private String csTitle;
	
	@Column(name="cs_content")
	private String csContent;
	
	@Column(name="hit_count")
	private int hitCount;
	
	@Column(name="create_date")
	@CreationTimestamp 	// 게시글이 처음 생성될 때 자동으로 날짜 세팅
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	@LastModifiedDate	// 게시글이 수정된 마지막 날짜/시간을 세팅
	private LocalDateTime updateDate;
	
	// 첨부파일이 있을 경우 추가
	@Column(name="original_file_name")
	private String originalFileName;
	
	@Column(name="saved_file_name")
	private String savedFileName;
	
	// 댓글 개수 처리
	@Formula("(SELECT count(1) FROM reply r WHERE cs_num  = r.cs_num)")
	private int replyCount;

	public static CsEntity toEntity(CsDTO csDTO) {
		return CsEntity.builder()
				.csNum(csDTO.getCsNum()) 
				.csWriter(csDTO.getCsWriter())
				.csTitle(csDTO.getCsTitle())
				.csContent(csDTO.getCsContent())
				.hitCount(csDTO.getHitCount())
			
				.originalFileName(csDTO.getOriginalFileName())
				.savedFileName(csDTO.getSavedFileName())
//				.createDate(boardDTO.getCreateDate())
//				.updateDate(boardDTO.getUpdateDate())
				.build();
	}
}