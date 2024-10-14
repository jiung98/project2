package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.CsEntity;

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

public class CsDTO {
    private Long csno;
    private String title;
    private String content;
    private LocalDate postdate;

    private static CsDTO toDTO(CsEntity csEntity) {
    	return CsDTO.builder()
    			.csno(csEntity.getCsno())
    			.title(csEntity.getTitle())
    			.content(csEntity.getContent())
    			.postdate(csEntity.getPostdate())
    			.build();
    }
}