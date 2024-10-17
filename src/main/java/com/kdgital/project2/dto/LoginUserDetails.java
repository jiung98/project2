package com.kdgital.project2.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Security로 login 하는 DTO
@ToString
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userPwd;
	private String email;
	private String userAdd;
	private String phone;
	private String userName;
	private LocalDate birth;
	private String roles;
	private Boolean enabled;
	
	// 생성자
	public LoginUserDetails(UserDTO memberDTO) { 
		this.userId = memberDTO.getUserId();
		this.userPwd = memberDTO.getUserPwd();
		this.userName = memberDTO.getUserName();
		this.email = memberDTO.getEmail();
		this.roles = memberDTO.getRoles();
		this.enabled = memberDTO.getEnabled();
	}

	// 사용자의 Role의 정보 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return roles;
			}
		});

		return collection;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	// Security가 설정한 메소드 (사용자의 아이디)
	@Override
	public String getUsername() {

		return this.userId;
	}

	// 사용자 정의 메소드(뷰단에서 사용할 사용자의 실명 이름)
	public String getUserName() {
		return this.userName;
	}
}
