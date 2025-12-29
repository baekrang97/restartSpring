package com.hello.hello_spring.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hello.hello_spring.domain.Member;

public interface MemberRepository {
	Member save(Member member) throws SQLException;
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
