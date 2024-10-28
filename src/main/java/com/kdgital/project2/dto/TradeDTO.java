package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.entity.TradeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Data

public class TradeDTO {
	private Long tradeNo;
	private LocalDate tradeDate;
	private String cCode;
	private String rCode;
	private Long export;
	private Long importValue;

	private static TradeDTO toDTO(TradeEntity tradeEntity, String rCode, String cCode) {
		return TradeDTO.builder()
				.tradeNo(tradeEntity.getTradeNo())
				.tradeDate(tradeEntity.getTradeDate())
				.export(tradeEntity.getExport())
				.importValue(tradeEntity.getImportValue())
				.rCode(tradeEntity.getRCode())
				.cCode(tradeEntity.getCCode())
				.build();
	}
	// 수출용 생성자
    public TradeDTO(LocalDate tradeDate, Long export) {
        this.tradeDate = tradeDate;
        this.export = export;
    }

    // 수입용 생성자
    public TradeDTO(LocalDate tradeDate, Long importSum, boolean isImport) {
        this.tradeDate = tradeDate;
        this.importValue = importSum;
    }
    
 // 생성자 추가
    public TradeDTO(LocalDate tradeDate, String rCode, String cCode, Long export, Long importValue) {
        this.tradeDate = tradeDate;
        this.rCode = rCode;
        this.cCode = cCode;
        this.export = export;
        this.importValue=importValue;
    }
}