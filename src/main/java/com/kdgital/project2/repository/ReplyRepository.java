package com.kdgital.project2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgital.project2.entity.BoardEntity;
import com.kdgital.project2.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

	List<ReplyEntity> findAllByBoardEntityOrderByReplyNumDesc(Optional<BoardEntity> boardEntity);

}
