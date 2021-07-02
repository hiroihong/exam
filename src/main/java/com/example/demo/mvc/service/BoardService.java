package com.example.demo.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.domain.PageRequestParameter;
import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.parameter.BoardParameter;
import com.example.demo.mvc.parameter.BoardSearchParameter;
import com.example.demo.mvc.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository repository;

	public List<Board> getList(PageRequestParameter<BoardSearchParameter> parameter) {
		return repository.getList(parameter);
	}

	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}

	public int save(BoardParameter board) {
		// 조회해서 리턴된 정보가 있으면 업데이트
		Board tmp = repository.get(board.getBoardSeq());
		if (tmp == null) {
			repository.save(board);
		} else {
			repository.update(board);
		}
		return board.getBoardSeq();
	}

	public void saveList1(List<BoardParameter> list) {
		for (BoardParameter parameter : list) {
			repository.save(parameter);
		}
	}
	
	public void saveList2(List<BoardParameter> list) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("boardList", list);
		repository.saveList(paramMap);
	}

//	public void update(Board board) {
//		repository.update(board);
//	}

	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}

}
