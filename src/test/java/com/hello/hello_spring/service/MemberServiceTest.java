package com.hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	
	/*
	 * 각각의 테스트가 끝날때마다 실행되는 AfterEach를 활용해 
	 * clearStore 함수를 끝날때마다 실행시켜 객체를 초기화 시킴
	 */
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	
	@Test
	void join() throws SQLException {
		//given
		Member member = new Member();
		member.setName("hello");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member result = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(result.getName());
	}
	
	@Test
	void findMembers() throws SQLException {
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		memberService.join(member1);
		
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
	}

	@Test
	void findOne() {
		
	}
}

