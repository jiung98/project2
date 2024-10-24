package com.kdgital.project2.dto;

import java.time.LocalDate;

import com.kdgital.project2.entity.RegionEntity;
import com.kdgital.project2.entity.dashboardEntity;

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
public class dashboardDTO {
    private Long mainNo;
    private String regionName;  // 지역 이름
    private Long production;    // 생산량
    private Integer year;       // 연도
    private Long totalProduction;  // 연도별 생산량 합계
    private RegionEntity regionEntity;  // RegionEntity 참조 추가
//
//    // Entity -> DTO 변환 메서드
//    public static dashboardDTO toDTO(dashboardEntity entity) {
//        return dashboardDTO.builder()
//                .mainNo(entity.getMainNo())
//                .regionName(entity.getRegionEntity().getRegionName())
//                .production(entity.getProduction())
//                .year(entity.getMainDate().getYear())  // LocalDate에서 연도만 추출
//                .build();
//    }
    
    // DTO -> Entity 변환 메서드
    public static dashboardEntity toEntity(dashboardDTO dto, RegionEntity regionEntity) {
        return dashboardEntity.builder()
                .mainNo(dto.getMainNo())
                .mainDate(LocalDate.of(dto.getYear(), 1, 1))  // 연도만으로 LocalDate 생성
                .production(dto.getProduction())
                .regionEntity(regionEntity)
                .build();
    }
}
