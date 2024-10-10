package com.kdigital.project2.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "cs")
public class CSEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int csno;

	private String title;
	private String content;

	@Temporal(TemporalType.DATE)
	private LocalDateTime postdate;


	@OneToMany(mappedBy = "cs", cascade = CascadeType.ALL)
	private List<CSAnswerEntity> answers;

}