package com.pvrschcms.pvrcinemaschdulernew.constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private String errorMessage;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
}
