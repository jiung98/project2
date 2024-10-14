package com.kdgital.project2.dto;

import java.time.LocalDate;

import com.kdgital.project2.entity.MemberEntity;

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

public class MemberDTO {
    private String userId;
    private String userPwd;
    private String email;
    private String userAdd;
    private String phone;
    private LocalDate birth;
    private String roles;
    private Boolean enabled;

    public static MemberDTO toDTO(MemberEntity memberEntity) {
    	return MemberDTO.builder()	
    			.userId(memberEntity.getUserId())
				.userPwd(memberEntity.getUserPwd())
				.email(memberEntity.getEmail())
				.userAdd(memberEntity.getUserAdd())
				.phone(memberEntity.getPhone())
				.birth(memberEntity.getBirth())
				.roles(memberEntity.getRoles())
				.enabled(memberEntity.getEnabled())
				.build();  
    }
}