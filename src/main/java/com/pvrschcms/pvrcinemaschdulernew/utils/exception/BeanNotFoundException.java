package com.pvrschcms.pvrcinemaschdulernew.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeanNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeanNotFoundException(String message) {
		super(message);
	}
}
