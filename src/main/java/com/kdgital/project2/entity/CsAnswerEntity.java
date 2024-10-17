package com.kdgital.project2.entity;

import com.kdgital.project2.dto.CsAnswerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "csanswer")
public class CsAnswerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="answer_no")
	private Long answerNo;

	@Column(name = "answer", nullable = false)
    private String answer;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "csno")
	private CsEntity csEntity;
	
	private static CsAnswerEntity toEntity(CsAnswerDTO csAnswerDTO, CsEntity csEntity) {
		return CsAnswerEntity.builder()
				.answerNo(csAnswerDTO.getAnswerNo())
				.answer(csAnswerDTO.getAnswer())
				.csEntity(csEntity)
				.build();
	}
}