package com.example.demo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Test
	public void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			//1부터 100까지의 임의의 번호
			long bno  = (long)(Math.random() * 100) + 1;
			Board board = Board.builder().bno(bno).build();   // 게시물의 번호
			Reply reply = Reply.builder()
					.text("Reply......." +i)
					.board(board)
					.replyer("guest")
					.build();
			
			replyRepository.save(reply);
			});
		}	
}
