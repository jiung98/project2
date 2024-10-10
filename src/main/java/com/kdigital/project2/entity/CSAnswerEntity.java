package com.kdigital.project2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
@Table(name = "csanswer")
public class CSAnswerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerno;

	private String answer;

	@ManyToOne
	@JoinColumn(name = "csno", nullable = false)
	private CSEntity customerSupport;

}