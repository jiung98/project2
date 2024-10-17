package com.kdgital.project2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.LoginUserDetails;
import com.kdgital.project2.dto.UserDTO;
import com.kdgital.project2.entity.UserEntity;
import com.kdgital.project2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {
	final MemberRepository memberRepository;

	// UserId 검증 로직 추가. DB 테이블에서 데이터를 가져옴
	// 사용자가 login을 하면 SecurityConfig 가로챈 후 이쪽으로 데이터를 전달
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity memberEntity = memberRepository.findById(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("error 발생");
				});

		UserDTO memberDTO= UserDTO.toDTO(memberEntity);

		// 반환을 UserDetails로 반환해야 하므로 UserDTO를 UserDetails로 바꿈!
		return new LoginUserDetails(memberDTO);
	}
}
 