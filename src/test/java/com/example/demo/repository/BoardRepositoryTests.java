package com.example.demo.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.service.BoardService;

@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			Member member = Member.builder().email("user"+i +"@aaa.com").build();
			Board board = Board.builder()
					.title("Title..."+i)
					.content("Content...." + i)
					.writer(member)
					.build();
			boardRepository.save(board);
			});
		}
	
	@Test
	public void testRead1() {
		
		Optional<Board> result = boardRepository.findById(100L); //데이터베이스에 존재하는 번호
		
		Board board = result.get();
		
		System.out.println(board);
		System.out.println(board.getWriter());
		}
	
	
	@Test
	public void testRead3() {
		Object result = boardRepository.getBoardByBno(100L);
		Object[] arr = (Object[])result;
		System.out.println(Arrays.toString(arr));
		}
	
	}

	
