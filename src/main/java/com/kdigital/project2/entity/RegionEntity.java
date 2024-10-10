package com.kdigital.project2.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "regions")
public class RegionEntity {
    @Id
    private String r_code;

    private String region;

    @OneToMany(mappedBy = "region")  //
    private List<MainServiceEntity> mainServices;

    @OneToMany(mappedBy = "region")
    private List<TradeEntity> trades;

}