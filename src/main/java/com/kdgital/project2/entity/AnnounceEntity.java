package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.LastModifiedDate;

import com.kdgital.project2.dto.AnnounceDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "announce")
public class AnnounceEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serno")
    private Long serno;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name="create_date")
	@CreationTimestamp 	// 게시글이 처음 생성될 때 자동으로 날짜 세팅
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	@LastModifiedDate	// 게시글이 수정된 마지막 날짜/시간을 세팅
	private LocalDateTime updateDate;
	
    @Column(name="writer", nullable = false)
	private String writer;
    
    @Column(name="hit_count")
	private int hitCount;
    


    public static AnnounceEntity toEntity(AnnounceDTO announceDTO) {
    	return AnnounceEntity.builder()
    			.serno(announceDTO.getSerno())
    			.title(announceDTO.getTitle())
    			.content(announceDTO.getContent())
    			.createDate(announceDTO.getCreateDate())
    			.updateDate(announceDTO.getUpdateDate())
    			.writer(announceDTO.getWriter())
    			.hitCount(announceDTO.getHitCount())
    			
//				.createDate(boardDTO.getCreateDate())
//				.updateDate(boardDTO.getUpdateDate())
				.build();
    }
}