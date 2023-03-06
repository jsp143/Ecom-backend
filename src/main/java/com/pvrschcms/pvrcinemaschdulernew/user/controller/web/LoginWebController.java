package com.pvrschcms.pvrcinemaschdulernew.user.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pvrschcms.pvrcinemaschdulernew.user.model.request.LoginRequest;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pvrschcms.pvrcinemaschdulernew.user.model.response.LoginResponse;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.service.MyUserDetailsService;


@Controller
@RequestMapping("/web/admin/")
public class LoginWebController {
	Logger logger = LoggerFactory.getLogger("ws");
	@Autowired
    private MyUserDetailsService userService;

	@Autowired
	private ValidationUtils validationUtils;

	@RequestMapping("login")
	public String loginPage(Model model) {
	    return "admin-login";
	}
	
	@RequestMapping("login/dashboard")
	public ModelAndView loginRequest(HttpServletRequest request, HttpServletResponse response , @RequestBody LoginRequest loginRequest, Model model) {
		logger.debug("LOGIN PROCCESS {} password {}",loginRequest.getUsername(),loginRequest.getPassword());
		ModelAndView andView =  new ModelAndView();
		try {
			if(validationUtils.validateLoginRequest(loginRequest)) {
				LoginResponse resp = userService.authenticateUserWeb(loginRequest.getUsername(),loginRequest.getPassword());
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				UserModel userDetails = null;
				userDetails = (UserModel) principal;
				logger.debug("LOGIN PROCCESS getUsername :: {}  getAccessToken :: {} :: type :: {} ",userDetails.getUsername(),resp.getAccessToken(),resp.getTokenType());

				if(response!=null) {
					logger.debug("LOGIN PROCCESS getUsername if cond :: {} ",userDetails.getUsername());
					andView.addObject("userDetails", userDetails);
					andView.setViewName("admin-dashboard");
					return andView;
				}else {
					logger.debug("LOGIN PROCCESS getUsername else cond :: {} ",userDetails.getUsername());
					andView.setViewName(Constant.Redirect.LOGIN_PAGE);
					return andView;
				}
			}else {
				andView.setViewName(Constant.Redirect.LOGIN_PAGE);
				return andView;
			}
		}catch (Exception e) {
			logger.debug("LOGIN PROCCESS excption loginRequest() {}",e.getMessage());
			andView.setViewName(Constant.Redirect.LOGIN_PAGE);
			return andView;
		}
	}
	
	@RequestMapping("dashboard")
	public ModelAndView adminDashboard(Model model) {
		ModelAndView andView =  new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel userDetails = null;
		userDetails = (UserModel) principal;
		andView.addObject("userDetails", userDetails);
		andView.setViewName("admin-dashboard");
		return andView;
	}
}