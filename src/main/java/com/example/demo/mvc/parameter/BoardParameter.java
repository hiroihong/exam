package com.example.demo.mvc.parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardParameter {

	private int boardSeq;
	private String title;
	private String contents;
	
//	public BoardParameter() {
//		
//	}
//	
//	public BoardParameter(String title, String contents) {
//		this.title = title;
//		this.contents = contents;
//	}
}
