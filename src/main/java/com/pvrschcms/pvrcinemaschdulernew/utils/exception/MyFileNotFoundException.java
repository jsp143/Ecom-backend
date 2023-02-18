package com.pvrschcms.pvrcinemaschdulernew.utils.exception;

public class MyFileNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
