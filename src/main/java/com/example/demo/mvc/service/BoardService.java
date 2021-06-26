package com.example.demo.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository repository;

	public List<Board> getList() {
		return repository.getList();
	}

	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}

	public int save(Board board) {
		// 조회해서 리턴된 정보가 있으면 업데이트
		Board tmp = repository.get(board.getBoardSeq());
		if(tmp == null) {
			repository.save(board);
		} else {
			repository.update(board);
		}
		return board.getBoardSeq();
	}

//	public void update(Board board) {
//		repository.update(board);
//	}

	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}

}
