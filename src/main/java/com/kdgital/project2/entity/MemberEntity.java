package com.kdgital.project2.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.kdgital.project2.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="members")
public class MemberEntity {
	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name = "user_pwd")
	private String userPwd;

	@Column(name = "email")
	private String email;

	@Column(name = "user_add")
	private String userAdd;

	@Column(name = "phone")
	private String phone;

	@Column(name = "birth")
	private LocalDate birth;  

	@Column(name = "roles")
	private String roles;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	
	public static MemberEntity toEntity(MemberDTO memberDTO) {
		return MemberEntity.builder()
				.userId(memberDTO.getUserId())
				.userPwd(memberDTO.getUserPwd())
				.email(memberDTO.getEmail())
				.userAdd(memberDTO.getUserAdd())
				.phone(memberDTO.getPhone())
				.birth(memberDTO.getBirth())
				.roles(memberDTO.getRoles())
				.enabled(memberDTO.getEnabled())
				.build();  
	}

}
