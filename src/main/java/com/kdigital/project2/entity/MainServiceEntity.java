package com.kdigital.project2.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.plaf.synth.Region;

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
@Table(name = "mainservice")
public class MainServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mainno;

    @Temporal(TemporalType.DATE)
    private LocalDateTime maindate;
    
    private int production;
    private BigDecimal temperatue;

    @ManyToOne
    @JoinColumn(name = "r_code", nullable = false)
    private Region region;

}