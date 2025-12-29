package com.hello.hello_spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/*
	 * 회원가입
	 */
	public Long join(Member member) throws SQLException {
		
		// Alt + Shift + M 으로 extract method로 변경
		validateDuplicateMember(member);
		
		memberRepository.save(member);
		return member.getId();
	}

	/*
	 * 중복된 회원이 있는지 확인
	 */
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalArgumentException("이미 존재하는 회원입니다.");
				});
	}
	
	/*
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
