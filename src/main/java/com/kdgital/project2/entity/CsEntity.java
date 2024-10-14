package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
	@Column(name = "csno")
	private Long csno;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "postdate")
	private LocalDate postdate;

	private static CsEntity toEntity(CsDTO csDTO) {
		return CsEntity.builder()
				.csno(csDTO.getCsno())
				.title(csDTO.getTitle())
				.content(csDTO.getContent())
				.postdate(csDTO.getPostdate())
				.build(); 
	}
}