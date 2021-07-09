package com.example.demo.mvc.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.mvc.parameter.UploadFileParameter;

@Repository
public interface UploadFileRepository {

	void save(UploadFileParameter parameter);
	
}
