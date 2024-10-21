package com.kdgital.project2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kdgital.project2.dto.ReplyDTO;
import com.kdgital.project2.entity.CsEntity;
import com.kdgital.project2.entity.ReplyEntity;
import com.kdgital.project2.repository.CsRepository;
import com.kdgital.project2.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
	final CsRepository csRepository;
	final ReplyRepository replyRepository;
	
	@Transactional
	public ReplyDTO replyInsert(ReplyDTO replyDTO) {
		// 부모의 게시글이 존재하는 여부 확인
		Optional<CsEntity> csEntity = csRepository.findById(replyDTO.getCsNum());
		if(csEntity.isPresent()) {
			CsEntity entity = csEntity.get();  // 부모글을 꺼냄.
			System.out.println(entity);
			ReplyEntity replyEntity = ReplyEntity.toEntity(replyDTO, entity);
			
			ReplyEntity temp = replyRepository.save(replyEntity);
			return ReplyDTO.toDTO(temp, replyDTO.getCsNum());
		}
		return null;
	}

	public List<ReplyDTO> replyAll(Long csNum) {
		Optional<CsEntity> csEntity = csRepository.findById(csNum);
		
		List<ReplyEntity> replyEntityList 
			= replyRepository.findAllByCsEntityOrderByReplyNumDesc(csEntity);
		
		/* Entity --> DTO로 변환 */
		List<ReplyDTO> replyDTOList = new ArrayList<>();
		
		replyEntityList.forEach(
				(entity) -> replyDTOList.add(ReplyDTO.toDTO(entity, csNum)));
		
		System.out.println("=================" + replyDTOList);
		return replyDTOList;
	}

	/**
	 * 댓글 삭제
	 * @param replyNum : 댓글 번호
	 * @return
	 */
	public boolean replyDelete(Long replyNum) {
		replyRepository.deleteById(replyNum);  // 삭제 후
		
		return replyRepository.existsById(replyNum);
	}
	
}









