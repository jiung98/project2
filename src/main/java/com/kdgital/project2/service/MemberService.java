package com.kdgital.project2.service;

import org.springframework.stereotype.Service;

import com.kdgital.project2.entity.MemberEntity;
import com.kdgital.project2.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor //객체생성하는거
public class MemberService {
	private final MemberRepository member; // final 있어야 공유한다는거
	@Transactional 
	public void saveMember(MemberEntity memberEntity) {
		System.out.println("11");
		member.save(memberEntity);
		System.out.println("aa");
	}
} 
