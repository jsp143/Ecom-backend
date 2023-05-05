package com.pvrschcms.pvrcinemaschdulernew.user.controller.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pvrschcms.pvrcinemaschdulernew.user.model.response.LoginResponse;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.service.MyUserDetailsService;

import java.security.Principal;


@Controller
@RequestMapping("/web/admin/")
public class LoginWebController {
	Logger logger = LoggerFactory.getLogger("ws");
	@Autowired
    private MyUserDetailsService userService;
	@Autowired
	private Utility utility;
	@Autowired
	private ValidationUtils validationUtils;

	@RequestMapping("login")
	public ModelAndView loginPage(Model model) {
		ModelAndView andView =  new ModelAndView();
	    UserModel userModel = getPrinciple();
		if (userModel!=null){
			andView.setViewName(Constant.Redirect.DASHBOARD);
			return andView;
		}else {
			andView.setViewName("admin-login");
			return andView;
		}
	}
	
	@RequestMapping("authenticate")
	public ModelAndView loginRequest(HttpServletRequest request, HttpServletResponse response , @RequestParam String username, @RequestParam String password, Model model) {
		logger.debug("LOGIN PROCCESS {} password {}",username,password);
		ModelAndView andView =  new ModelAndView();
		try {
			if(validationUtils.validateWebLoginRequest(username,password)) {
				LoginResponse resp = userService.authenticateUserWeb(username,password);
				//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				//UserModel userDetails = utility.getPrinciple(SecurityContextHolder.getContext());
				//UserModel userDetails = null;
				//userDetails = (UserModel) principal;
				UserModel userDetails = getPrinciple();
				logger.debug("LOGIN PROCESS getUsername 53 :: {}  getAccessToken :: {} :: type :: {} ",userDetails.getUsername(),resp.getAccessToken(),resp.getTokenType());

				if(response!=null) {
					logger.debug("LOGIN PROCESS getUsername if cond :: {} ",userDetails.getUsername());
					andView.addObject("userDetails", userDetails);
//					model.addAttribute("usermodel",userDetails);
					andView.setViewName("admin-dashboard");
					//setting to session
					request.getSession().setAttribute("token", resp.getAccessToken());
					request.getSession().setAttribute("tokenType", resp.getTokenType());
					return andView;
				}else {
					logger.debug("LOGIN PROCESS getUsername else cond :: {} ",userDetails.getUsername());
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
	public ModelAndView adminDashboard(HttpServletRequest request, Model model, HttpServletResponse response) {
		logger.debug("ADMIN DASHBOARD response {} ",new Gson().toJson(request.getUserPrincipal()));
		ModelAndView andView =  new ModelAndView();
		try {
//			UserModel userDetails =getPrinciple();
//			model.addAttribute("usermodel",userDetails);
//			//UserModel userDetails =  utility.getPrinciple(SecurityContextHolder.getContext());
//			logger.debug("ADMIN DASHBOARD 81 getUsername {} getMobile {} ",userDetails.getUsername(),userDetails.getMobile());
//			andView.addObject("userDetails", userDetails);
			andView.setViewName("admin-dashboard");
			return andView;
		}catch (Exception e){
			logger.debug("ADMIN DASHBOARD excption adminDashboard() {}",utility.error(e));
			andView.setViewName(Constant.Redirect.LOGIN_PAGE);
			return andView;
		}

	}

	private UserModel getPrinciple(){
		UserModel userModel = null;
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserModel){
			userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return userModel;
	}
}