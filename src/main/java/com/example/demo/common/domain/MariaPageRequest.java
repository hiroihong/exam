package com.example.demo.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 페이지 요청 정보 및 계산된 값
 * 
 * @author areha
 *
 */
@Data
public class MariaPageRequest {

	private int page;
	private int size;

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private int limit;

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private int offset;

	public MariaPageRequest(int page, int size, int limit, int offset) {
		super();
		this.page = page;
		this.size = size;
		this.limit = limit;
		this.offset = offset;
	}

}
