package com.kdgital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kdgital.project2.dto.dashboardDTO;

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
@Table(name = "mainservice")
public class dashboardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "main_no")
	private Long mainNo;

	@Column(name = "main_date")
	private LocalDate mainDate;

	@Column(name = "production", nullable = false)
	private Long production;

	@Column(name = "temperature", nullable = false)  // 오타 수정 "temperature"
	private BigDecimal temperature;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "r_code")
	private RegionEntity regionEntity;  // RegionEntity 참조

//	// Entity -> DTO 변환 메서드
//	public static dashboardDTO toDTO(dashboardEntity entity) {
//		return dashboardDTO.builder()
//				.mainNo(entity.getMainNo())
//				.regionName(entity.getRegionEntity().getRegionName())
//				.production(entity.getProduction())
//				.year(entity.getMainDate().getYear())  // LocalDate에서 연도 추출
//				.build();
//	}

	// DTO -> Entity 변환 메서드
	public static dashboardEntity toEntity(dashboardDTO dto, RegionEntity regionEntity) {
		return dashboardEntity.builder()
				.mainNo(dto.getMainNo())
				.mainDate(LocalDate.of(dto.getYear(), 1, 1))  // 연도만을 사용해 LocalDate 생성
				.production(dto.getProduction())
				.regionEntity(regionEntity)
				.build();
	}
}
