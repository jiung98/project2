package com.kdigital.project2.entity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {
	@Id
	@Column(name="userid")
	private String userid;
	
	@Column(name="userpwd")
	private String userpwd;
	
	@Column(name="email")
	private String email;
	
	@Column(name="useradd")
	private String useradd;
	
	@Column(name="phone")
	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name="birth")
	private LocalDateTime birth;  

	private String userrole = "ROLE_USER";
	private char enabled = '1';

}

