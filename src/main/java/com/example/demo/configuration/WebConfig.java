package com.example.demo.configuration;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.common.domain.MariaPageRequest;
import com.example.demo.common.web.MariaPageRequestHandleMethodArgumentResolver;
import com.example.demo.configuration.servlet.handler.BaseHandlerInterceptor;
import com.example.demo.mvc.domain.BaseCodeLabelEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

//WebMvcConfigurer에 addInterceptors할 수 있는 메소드가 정의되어 있기 때문에 implements
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	public GlobalConfig globalConfig;

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean
	public BaseHandlerInterceptor baseHandlerInterceptor() {
		return new BaseHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Bean으로 만든 BaseHandlerInterceptor를 등록
		registry.addInterceptor(baseHandlerInterceptor());
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}

	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
		jsonView.setObjectMapper(objectMapper());
		return jsonView;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// 페이지 리졸버 등록
		// 자바파일 실행시에 매개변수 넣어서 실행하는 역활을 해줌.
		resolvers.add(new MariaPageRequestHandleMethodArgumentResolver());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 업로드 파일 static resource 접근 경로
		String resourcePattern = globalConfig.getUploadResourcePath() + "**";

		// 로컬(윈도우 환경)
		if (globalConfig.isLocal()) {
			registry.addResourceHandler(resourcePattern)
					.addResourceLocations("file:///" + globalConfig.getUploadFilePath());
		} else {

			// 리눅스 또는 유닉스 환경
			registry.addResourceHandler(resourcePattern)
					.addResourceLocations("file:" + globalConfig.getUploadFilePath());

		}

	}

}
