package com.example.demo.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired BoardService boardService;

	@GetMapping
	public List<Board> getList(){
		return boardService.getList();
	}
	
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	
	@GetMapping("/save")
	public int save(Board board) {
		return boardService.save(board);
	}
	
	@GetMapping("/delete/{boardSeq}")
	public void delete(@PathVariable int boardSeq) {
		boardService.delete(boardSeq);
	}
	
	public void update(Board board) {
		boardService.update(board);
	}
	
}
