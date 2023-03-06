package com.pvrschcms.pvrcinemaschdulernew.user.controller;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ValidationUtils;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.service.UserService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/web/user/")
public class UserController {
	Logger logger = LoggerFactory.getLogger("ws");
	@Autowired
	private UserService usrservice;
	@Autowired
	private ValidationUtils validationUtils;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private Utility utility;

	@PostMapping(value = "signup/customer")
	public ResponseDto signUpCustomer(@RequestBody SignUpRequest request) {
		ResponseDto resp = new ResponseDto();
		try {
			if(validationUtils.validateUserRequest(request)) {
				request.setType(Constant.TypeUser.TYPE_COUSTOMER.toString());
				request.setRoleName(Constant.RoleName.ROLE_USER.toString());
				request.setRoles(Constant.RoleName.ROLE_USER.toString());
				UserModel userModel = usrservice.createCustomer(request);
				if (userModel != null) {
					resp.setSuccess(true);
					resp.setCode(Constant.RESP_ALERT_DIALOG);
					resp.setMessage(Constant.Message.USER_CREATED_SUCCESSFULY);
				} else {
					logger.debug("CREATE USER PROCESS :: ERROR ");
					resp.setSuccess(false);
					resp.setMessage(Constant.Message.ERROR);
					resp.setCode(Constant.RESP_ALERT_DIALOG);
				}
			}else{
				logger.debug("CREATE USER PROCESS :: USER_CREATE_REQUEST_INVALID ");
				resp.setSuccess(false);
				resp.setMessage(Constant.Message.USER_CREATE_REQUEST_INVALID);
				resp.setCode(Constant.RESP_ALERT_DIALOG);
			}
			return resp;
		}catch (Exception e) {
			logger.error("CREATE USER PROCESS :: EXCEPTION {}", utility.error(e));
			resp.setSuccess(false);
			resp.setCode(Constant.RESP_ALERT_ERROR);
			resp.setMessage(Constant.Message.ERROR);
			return resp;
		}

	}



}
