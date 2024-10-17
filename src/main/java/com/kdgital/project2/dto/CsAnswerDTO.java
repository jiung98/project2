package com.kdgital.project2.dto;


import com.kdgital.project2.entity.CsAnswerEntity;

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

public class CsAnswerDTO {
    private Long answerNo;
    private Long csno;
    private String answer;

    private static CsAnswerDTO toDTO(CsAnswerEntity csAnswerEntity, Long csno) {
    	return CsAnswerDTO.builder()
    			.answerNo(csAnswerEntity.getAnswerNo())
    			.csno(csno)
    			.answer(csAnswerEntity.getAnswer())
    			.build();
    }
}