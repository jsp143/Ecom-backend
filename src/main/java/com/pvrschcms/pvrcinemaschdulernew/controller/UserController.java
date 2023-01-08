package com.pvrschcms.pvrcinemaschdulernew.controller;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.model.user.response.UserResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.model.user.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.service.UserService;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/web/user/")
public class UserController {
	
	@Autowired
	private UserService usrservice;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "signup/customer")
	public ResponseDto signUpCustomer(@RequestBody SignUpRequest request) {
		ResponseDto resp = new ResponseDto();
		try {
			UserModel userModel = usrservice.createCustomer(request);
			if(userModel!=null){
				resp.setSuccess(true);
				resp.setCode(Constant.RESP_ALERT_DIALOG);
				resp.setMessage(Constant.Message.USER_CREATED_SUCCESSFULY);
			}else{
				resp.setSuccess(false);
				resp.setMessage(Constant.Message.ERROR);
				resp.setCode(Constant.RESP_ALERT_DIALOG);
			}
			return resp;
		}catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(e.getMessage());
			return resp;
		}

	}

	//@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	@PostMapping(value = "create/user")
	public ResponseDto signUpUser(@RequestBody SignUpRequest request) {
		ResponseDto resp = new ResponseDto();
		try {
			UserModel userModel = usrservice.createCustomer(request);
			if(userModel!=null){
				resp.setSuccess(true);
				resp.setCode(Constant.RESP_ALERT_DIALOG);
				resp.setMessage(Constant.Message.USER_CREATED_SUCCESSFULY);
			}else{
				resp.setSuccess(false);
				resp.setMessage(Constant.Message.ERROR);
				resp.setCode(Constant.RESP_ALERT_DIALOG);
			}
			return resp;
		}catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(e.getMessage());
			return resp;
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "customer/list")
	public ResponseDto getAllCoustomer() {
		ResponseDto resp = new ResponseDto();
		try {
			List<UserModel> userlist = usrservice.findAllCoustomer();
			System.out.println(" user list :: "+new Gson().toJson(userlist).toString());
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			List<UserResponse> userResponses = new ArrayList<>();
			BeanUtils.copyProperties(userlist, userResponses);
			resp.setData(userResponses);
		}catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@PostMapping(value = "my/detail")
	public ResponseDto getmyDetail(Authentication authentication) {
		String tkn = jwtTokenProvider.generateToken(authentication);
		ResponseDto resp = new ResponseDto();
		try {
			UserModel user = usrservice.findmyDetail(tkn);
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			resp.setData(userResponse);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "user/detail")
	public ResponseDto getUserDetail(@RequestParam String id) {
		ResponseDto resp = new ResponseDto();
		try {
			UserModel user = usrservice.findUserDetail(id);
			resp.setCode(Constant.RESP_SUCCESS);
			resp.setMessage(Constant.Message.SUCCESS);
			resp.setSuccess(true);
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			resp.setData(userResponse);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			resp.setSuccess(false);
		}
		return resp;
	}

}
