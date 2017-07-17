package com.ffp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not found in the system")
public class FFPExceptionHandler extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FFPExceptionHandler() {
		super();
	}

	public FFPExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	public FFPExceptionHandler(String message) {
		super(message);
	}

	public FFPExceptionHandler(Throwable cause) {
		super(cause);
	}

}
