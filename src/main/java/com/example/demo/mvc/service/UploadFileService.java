package com.example.demo.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mvc.parameter.UploadFileParameter;
import com.example.demo.mvc.repository.UploadFileRepository;

@Service
public class UploadFileService {

	@Autowired
	UploadFileRepository repository;
	
	public void save(UploadFileParameter parameter) {
		repository.save(parameter);
		
	}
	
}
