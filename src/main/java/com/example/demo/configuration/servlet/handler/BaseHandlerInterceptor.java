package com.example.demo.configuration.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.common.web.RequestConfig;
import com.example.demo.configuration.exception.BaseException;
import com.example.demo.configuration.http.BaseResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


//HandlerInterceptorAdapter는 비추천대상이됨.
//비추천 이유
//1.동작이 안전하지 않다
//2.미래 폐지 될 가능성이 있다.
//3.호환 가능한 클래스나 메소드가 준비되어 있다.
//@deprecated 주석으로 비추천 경고를 내지 않도록 할 수는 있다.
//public class BaseHandlerInterceptor extends HandlerInterceptorAdapter{}


//HandlerInterceptorAdapter의 경우 상속 extends
//HandlerInterceptor는 구현 implements
//작동에 차이는 없다.
//HandlerInterceptor interface는 default 메소드로 정의되어 있다.
//default 메소드는 자바 8부터 지원
//default로 선언되면 인터페이스에서 메소드를 구현 할 수 있다.
//인터페이스에서 메소드가 구현되어있기 때문에 오버라이드가 강제되지 않는다.
//static 메소드도 자바 8부터 지원, 상속 불가
//private 메소드는 자바 9부터 지원, 상속 불가, 구현체에서 구현 x
//interface에서 메소드 구현이 가능해졌기 때문에 추상 클래스와 차이점이 많이 없어짐
// 추상클래스는 private 변수를 선언하고 상태정보를 가질 수 있으니까
// private 변수가 필요할 경우 추상클래스를 사용하자

// BaseHandlerInterceptor를 bean으로 등록하자. -> WebConfig.java
public class BaseHandlerInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle requestURI : {} ", request.getRequestURI());

		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			logger.info("handlerMethod:{}",handlerMethod);
			RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
			if(requestConfig != null){
				if(requestConfig.loginCheck()){
					throw new BaseException(BaseResponseCode.LOGIN_REQUIRED);
				}
			}
		}


		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle requestURI : {} ", request.getRequestURI());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}

