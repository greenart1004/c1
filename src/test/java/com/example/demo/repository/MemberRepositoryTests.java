package com.example.demo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
	public class MemberRepositoryTests {
		
		@Autowired
		private MemberRepository memberRepository;
		
		@Test
		public void insertMembers() {
			IntStream.rangeClosed(101,200).forEach(i -> {
				Member member = Member.builder()
						.email("user"+i +"@aaa.com")
						.password("1111")
						.name("USER"+i)
						.build();
				memberRepository.save(member);
				});
			}
		}
	

