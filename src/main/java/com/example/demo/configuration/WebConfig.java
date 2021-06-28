package com.example.demo.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.configuration.servlet.handler.BaseHandlerInterceptor;


//WebMvcConfigurer에 addInterceptors할 수 있는 메소드가 정의되어 있기 때문에 implements
@Configuration
public class WebConfig implements WebMvcConfigurer{

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
		//Bean으로 만든 BaseHandlerInterceptor를 등록
		registry.addInterceptor(baseHandlerInterceptor());
	}
}
