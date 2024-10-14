package com.kdgital.project2.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.kdgital.project2.dto.FaqDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "faq")
public class FaqEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="faq_no")
	private Long faqNo;
	
	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "answer", nullable = false)
	private String answer;


	private static FaqEntity toEntity(FaqDTO faqDTO) {
		return FaqEntity.builder()
				.faqNo(faqDTO.getFaqNo())
				.question(faqDTO.getQuestion())
				.answer(faqDTO.getAnswer())
				.build();
	}
}