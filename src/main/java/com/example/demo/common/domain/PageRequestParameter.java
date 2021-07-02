package com.example.demo.common.domain;

import lombok.Data;

/**
 * 페이지 요청정보와 파라미터 정보
 * @author areha
 *
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {

	private MariaPageRequest pageRequest;
	private T parameter;

	public PageRequestParameter(MariaPageRequest pageRequest, T parameter) {
		super();
		this.pageRequest = pageRequest;
		this.parameter = parameter;
	}

}
