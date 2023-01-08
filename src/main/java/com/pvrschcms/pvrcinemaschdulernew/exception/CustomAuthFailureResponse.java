package com.pvrschcms.pvrcinemaschdulernew.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.constant.ResponseDto;
@SuppressWarnings("rawtypes")
@Component
public class CustomAuthFailureResponse implements AuthenticationEntryPoint,AuthenticationFailureHandler  {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		ResponseDto resp = new ResponseDto(false,ex.getMessage(),Constant.RESP_ALERT_ACCESS_DENIED);
		response.getOutputStream().println(new Gson().toJson(resp).toString());
	}
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		ResponseDto resp = new ResponseDto(false,authException.getMessage(),Constant.RESP_ALERT_UNAUTHORIZED);
		response.getOutputStream().println(new Gson().toJson(resp));
		
	}

}
