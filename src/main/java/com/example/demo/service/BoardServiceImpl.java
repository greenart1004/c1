package com.example.demo.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResultDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board  = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0],(Member)en[1],(Long)en[2]));

       Page<Object[]> result = repository.getBoardWithReplyCount(
               pageRequestDTO.getPageable(Sort.by("bno").descending())  );


        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {                                            // 게시물의 조회

        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[])result;

        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }


    @Transactional
    @Override
    public void removeWithReplies(Long bno) { //삭제 기능 구현, 트랜잭션 추가 
    	        //댓글 부터 삭제 
    	replyRepository.deleteByBno(bno);   //ReplyRepository에 함수 추가
    	repository.deleteById(bno);
    	}

	
	@Transactional
	@Override 
	public void modify(BoardDTO boardDTO) {
	  
       Board board = repository.getOne(boardDTO.getBno());
  
       board.changeTitle(boardDTO.getTitle());
       board.changeContent(boardDTO.getContent());
 
       repository.save(board); 
	}
}