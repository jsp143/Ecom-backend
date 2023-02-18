package com.pvrschcms.pvrcinemaschdulernew.utils.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.utils.exception.BeanNotFoundException;
import com.pvrschcms.pvrcinemaschdulernew.utils.exception.ResourceNotFoundException;
import com.pvrschcms.pvrcinemaschdulernew.utils.exception.UnauthorizedException;
@SuppressWarnings("rawtypes")
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BeanNotFoundException.class)
	public ResponseEntity<ResponseDto> handleNotFoundException(BeanNotFoundException ex) {
		ResponseDto resp = new ResponseDto(false,ex.getMessage(),Constant.RESP_ALERT_PAGE_NOT_FOUND);
		return new ResponseEntity<ResponseDto>(resp, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDto> unauthorizedException(UnauthorizedException ex) {
		ResponseDto response=new ResponseDto();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response.setCode(Constant.RESP_ALERT_ACCESS_DENIED);

        return new ResponseEntity<ResponseDto>(response, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ResponseDto resp = new ResponseDto(false,ex.getMessage(),Constant.RESP_ALERT_PAGE_NOT_FOUND);
		return new ResponseEntity<ResponseDto>(resp, HttpStatus.NOT_FOUND);
	}
}
