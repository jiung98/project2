package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.AnnounceEntity;

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

public class AnnounceDTO { 
    private Long serno;
    private String title;
    private String content;
    private LocalDate postdate;

    public static AnnounceDTO toDTO(AnnounceEntity announceEntity) {
    	return AnnounceDTO.builder()
    			.serno(announceEntity.getSerno())
    			.title(announceEntity.getTitle())
    			.content(announceEntity.getContent())
    			.postdate(announceEntity.getPostdate())
    			.build();
    			
    }
}
