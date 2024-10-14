package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.TradeEntity;

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

public class TradeDTO {
	private Long tradeNo;
	private LocalDate tradeDate;
	private String cCode;
	private String rCode;
	private Long export;
	private Long importC;

	private static TradeDTO toDTO(TradeEntity tradeEntity, String rCode, String cCode) {
		return TradeDTO.builder()
				.tradeNo(tradeEntity.getTradeNo())
				.tradeDate(tradeEntity.getTradeDate())
				.export(tradeEntity.getExport())
				.importC(tradeEntity.getImportC())
				.rCode(rCode)
				.cCode(cCode)
				.build();
	}
}