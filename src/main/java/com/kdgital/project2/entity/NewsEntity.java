package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.dto.NewsDTO;

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
@Table(name = "news")
public class NewsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serno")
	private Long serno;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "media", nullable = false)
	private String media;

	@Column(name = "postdate")
	private LocalDate postdate;

	private static NewsEntity toEntity(NewsDTO newsDTO) {
		return NewsEntity.builder()
				.serno(newsDTO.getSerno())
				.title(newsDTO.getTitle())
				.url(newsDTO.getUrl())
				.media(newsDTO.getMedia())
				.postdate(newsDTO.getPostdate())
				.build();
	}
}