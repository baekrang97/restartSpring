package com.hello.hello_spring.repository;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.hello.hello_spring.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	
	/*
	 * 각각의 테스트가 끝날때마다 실행되는 AfterEach를 활용해 
	 * clearStore 함수를 끝날때마다 실행시켜 객체를 초기화 시킴
	 */
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	/*
	 * MemoryMemberRepository 클래스의 save 기능을 테스트하는 Test 함수
	 */
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		
		/*
		System.out.println("result = " + (result == member));
		Assertions.assertEquals(result, member);
		*/
		
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	/*
	 * MemoryMemberRepository 클래스의 findByName 기능을 테스트하는 Test 함수
	 */
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();

		Assertions.assertThat(result).isEqualTo(member1);
	}

	/*
	 * MemoryMemberRepository 클래스의 findAll 기능을 테스트하는 Test 함수
	 */
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		List<Member> result = repository.findAll();

		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}
