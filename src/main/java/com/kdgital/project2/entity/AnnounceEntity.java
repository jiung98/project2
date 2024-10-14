package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "postdate")
    private LocalDate postdate;

    private static AnnounceEntity toEntity(AnnounceDTO announceDTO) {
    	return AnnounceEntity.builder()
    			.serno(announceDTO.getSerno())
    			.title(announceDTO.getTitle())
    			.content(announceDTO.getContent())
    			.postdate(announceDTO.getPostdate())
    			.build();
    }
}