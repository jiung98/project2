package com.kdgital.project2.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.kdgital.project2.dto.UserDTO;

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
@Table(name="users")
public class UserEntity {
	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name = "user_pwd")
	private String userPwd;
	
	@Column(name = "user_name")
	private String userName;

	@Column(name = "email")
	private String email;
//
//	@Column(name = "user_add")
//	private String userAdd;

	@Column(name = "phone")
	private String phone;

	@Column(name = "birth")
	private String birth;  

	@Column(name = "roles")
	private String roles;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.userId(userDTO.getUserId())
				.userPwd(userDTO.getUserPwd())
				.email(userDTO.getEmail())
				//.userAdd(userDTO.getUserAdd())
				.phone(userDTO.getPhone())
				.userName(userDTO.getUserName())
				.birth(userDTO.getBirth())
				.roles(userDTO.getRoles())
				.enabled(userDTO.getEnabled())
				.build();  
	}

}
