package com.example.demo.mvc.controller;

import com.example.demo.configuration.GlobalConfig;
import com.example.demo.configuration.http.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileContoller {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping
    @ApiOperation(value = "업로드",notes = "")
    public BaseResponse<Boolean> save(){
        String uploadFilePath = globalConfig.getUploadFilePath();
        logger.info("uploadfilepath : {}", uploadFilePath);

        return new BaseResponse<Boolean>(true);
    }

}
