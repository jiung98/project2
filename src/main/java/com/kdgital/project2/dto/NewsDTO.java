package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.NewsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class NewsDTO {
    private Long serno;
    private String title;
    private String url;
    private String media;
    private LocalDate postdate;

    public NewsDTO(Long serno, String title, String media, String url, LocalDate postdate) {
        this.serno = serno;
        this.title = title;
        this.media = media;
        // URL에 프로토콜이 없으면 자동으로 추가
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            this.url = "http://" + url;
        } else {
            this.url = url;
        }
        this.postdate = postdate;
    }
    
    private static NewsDTO toDTO(NewsEntity newsEntity) {
    	return NewsDTO.builder()
    			.serno(newsEntity.getSerno())
				.title(newsEntity.getTitle())
				.url(newsEntity.getUrl())
				.media(newsEntity.getMedia())
				.postdate(newsEntity.getPostdate()) 
				.build();
    }

}