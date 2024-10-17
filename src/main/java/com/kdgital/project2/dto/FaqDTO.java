package com.kdgital.project2.dto;

import com.kdgital.project2.entity.FaqEntity;

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

public class FaqDTO {
    private Long faqNo;
    private String question;
    private String answer;

    private static FaqDTO toDTO(FaqEntity faqEntity) {
    	return FaqDTO.builder()
    			.faqNo(faqEntity.getFaqNo())
				.question(faqEntity.getQuestion())
				.answer(faqEntity.getAnswer())
				.build();
    }
}