package com.kdigital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "demands")
public class DemandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int demandno;

    @Temporal(TemporalType.DATE)
    private LocalDateTime demanddate;

    private BigDecimal usa_yk;
    private BigDecimal usa_gk;
    private BigDecimal usa_gs;
    private BigDecimal jp_yk;
    private BigDecimal jp_ik;
    private BigDecimal jp_gk;

    private int demand;

 
}