package com.kdgital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.plaf.synth.Region;

import com.kdgital.project2.dto.MainServiceDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "mainservice")
public class MainServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "main_no")
	private Long mainNo;

	@Column(name = "main_date")
	private LocalDate mainDate;

	@Column(name = "production", nullable = false)
	private Long production;

	@Column(name = "temperatue", nullable = false)
	private BigDecimal temperature;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "r_code") //DB 기준?
	private RegionEntity regionEntity; // 왜 이렇게 쓰는거지??? 질문사항

	private static MainServiceEntity toEntity(MainServiceDTO mainServiceDTO, RegionEntity regionEntity) {
		return MainServiceEntity.builder()
				.mainNo(mainServiceDTO.getMainNo())
				.mainDate(mainServiceDTO.getMainDate())
				.production(mainServiceDTO.getProduction())
				.temperature(mainServiceDTO.getTemperature())
				.regionEntity(regionEntity)
				.build(); 
	}
}