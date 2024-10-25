package com.kdgital.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kdgital.project2.dto.TradeDTO;

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
@Table(name = "trade")
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_no")
    private Long tradeNo;

    @Column(name = "trade_date")
    private LocalDate tradeDate; 
    
    @Column(name = "export", nullable = false)
    private Long export;
    
    @Column(name = "import", nullable = false)
    private Long importValue;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "c_code")
    private CountryEntity countryEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "r_code")
    private RegionEntity regionEntity;
    
    private static TradeEntity toEntity(TradeDTO tradeDTO, CountryEntity countryEntity, RegionEntity regionEntity) {
    	return TradeEntity.builder()
    			.tradeNo(tradeDTO.getTradeNo())
    			.tradeDate(tradeDTO.getTradeDate())
    			.export(tradeDTO.getExport())
    			.importValue(tradeDTO.getImportValue())
    			.countryEntity(countryEntity)
    			.regionEntity(regionEntity)
    			.build(); 
    }
}