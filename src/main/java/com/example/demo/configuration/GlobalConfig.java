package com.example.demo.configuration;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties관련 Config
 * 
 * @author areha
 *
 */
@Configuration
public class GlobalConfig {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ResourceLoader resourceLoader;

	private String uploadFilePath;
	private String uploadResourcePath;
	private String schedulerPath;

	private Boolean local;

	private Boolean dev;

	private Boolean prod;

	@PostConstruct
	public void init() {
		logger.info("init");
		String[] activProfiles = context.getEnvironment().getActiveProfiles();
		String activProfile = "local";

		if (ObjectUtils.isNotEmpty(activProfiles)) {
			activProfile = activProfiles[0];
		}

		String resourcePath = String.format("classpath:/globals/global-%s.properties", activProfile);
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);

			uploadFilePath = properties.getProperty("uploadFile.path");
			uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
			schedulerPath = properties.getProperty("scheduler.cron.example");

			this.local = activProfile.equals("local");
			this.dev = activProfile.equals("dev");
			this.prod = activProfile.equals("prod");

		} catch (IOException e) {
			logger.error("e", e);
		}

	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public String getUploadResourcePath() {
		return uploadResourcePath;
	}

	public String getSchedulerPath() {
		return schedulerPath;
	}

	public boolean isLocal() {
		return local;
	}

	public boolean isDev() {
		return dev;
	}

	public boolean isProd() {
		return prod;
	}

}
