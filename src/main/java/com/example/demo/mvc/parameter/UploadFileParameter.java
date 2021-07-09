package com.example.demo.mvc.parameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadFileParameter {

	private String pathname;
	private String filename;
	private String originalFilename;
	private int size;
	private String contentType;
	private String resourcePathname;
}
