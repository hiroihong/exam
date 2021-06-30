package com.example.demo.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.parameter.BoardParameter;

@Repository
public interface BoardRepository {

	List<Board> getList();
		
	Board get(int boardSeq);
	
	int save(BoardParameter board);
	
	int saveList(Map<String, Object> paramMap);
	
	void delete(int boardSeq);
	
	void update(BoardParameter board);
	
}
