package com.example.demo.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.configuration.GlobalConfig;
import com.example.demo.configuration.exception.BaseException;
import com.example.demo.configuration.http.BaseResponse;
import com.example.demo.configuration.http.BaseResponseCode;
import com.example.demo.mvc.parameter.UploadFileParameter;
import com.example.demo.mvc.service.UploadFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileContoller {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig globalConfig;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping("/")
	public String save(Model model) {
		return "index";
	}

	@PostMapping("/save")
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam MultipartFile uploadFile) {

		logger.debug("uploadFile : {}", uploadFile);
		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
		}
		// 날짜 폴더 추가
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

		String uploadFilePath = globalConfig.getUploadFilePath() + currentDate + "/";
		logger.debug("uploadfilepath : {}", uploadFilePath);

		String prefix = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		String filename = UUID.randomUUID().toString() + "." + prefix;
		logger.info("filename : {}", filename);

		File folder = new File(uploadFilePath);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}

		String pathname = uploadFilePath + filename;
		String resourcePathname = globalConfig.getUploadResourcePath() + currentDate + "/" + filename;
		File dest = new File(pathname);
		logger.debug("dest : {}", dest);

		try {
			uploadFile.transferTo(dest);

			// 파일 업로드 후 DB에 저장
			UploadFileParameter uploadFileParameter = UploadFileParameter.builder().contentType(uploadFile.getContentType())
					.originalFilename(uploadFile.getOriginalFilename()).filename(filename).pathname(pathname)
					.size((int) uploadFile.getSize()).resourcePathname(resourcePathname).build();
			
			uploadFileService.save(uploadFileParameter);

		} catch (IllegalStateException | IOException e) {
			logger.error("e", e);
		}

		return new BaseResponse<Boolean>(true);
	}

}
