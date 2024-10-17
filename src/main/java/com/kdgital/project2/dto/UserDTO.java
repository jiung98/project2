package com.kdgital.project2.dto;

import java.time.LocalDate;

import com.kdgital.project2.entity.UserEntity;

//import com.kdigital.project2.entity.MemberEntity;

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

public class UserDTO {
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private String userAdd;
    private String phone;
    private LocalDate birth;
    private String roles;
    private Boolean enabled;

    public static UserDTO toDTO(UserEntity userEntity) {
    	return UserDTO.builder()	
    			.userId(userEntity.getUserId())
				.userPwd(userEntity.getUserPwd())
				.email(userEntity.getEmail())
				.userAdd(userEntity.getUserAdd())
				.userName(userEntity.getUserName())
				.phone(userEntity.getPhone())
				.birth(userEntity.getBirth())
				.roles(userEntity.getRoles())
				.enabled(userEntity.getEnabled())
				.build();  
    }
}