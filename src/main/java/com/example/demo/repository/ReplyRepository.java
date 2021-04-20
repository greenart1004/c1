package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Board;
import com.example.demo.entity.Reply;

public interface ReplyRepository  extends JpaRepository<Reply, Long>{
	
	@Modifying                                              // delete/update에 @Modifying 사용한다       
	@Query("delete from Reply r where r.board.bno =:bno")   // 한번에 댓글 다 삭제???
	void deleteByBno(Long bno);
	
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
