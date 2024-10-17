package com.kdgital.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdgital.project2.entity.UserEntity;

@Repository
public interface MemberRepository extends JpaRepository<UserEntity, String>  {

}
