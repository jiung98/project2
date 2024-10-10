package com.kdigital.project2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private int tradeno;

    @Temporal(TemporalType.DATE)
    private LocalDateTime tradedate; 

    private int export;
    
    @Column(name="import")
    private int import1;

    @ManyToOne
    @JoinColumn(name = "c_code", nullable = false)
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "r_code", nullable = false)
    private RegionEntity region;

    // Getters and Setters
}