package com.hello.hello_spring;

import javax.sql.DataSource;

import com.hello.hello_spring.aop.TimeTraceAop;
import com.hello.hello_spring.repository.JpaMemberRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.hello_spring.repository.JdbcMemberRepository;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.service.MemberService;

@Configuration
public class SpringConfig {

//	private final EntityManager em;

	private final MemberRepository memberRepository;

	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

//	@Bean
//	public MemberRepository memberRepository() {
//		return new JpaMemberRepository(em);
//	}

//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
}
