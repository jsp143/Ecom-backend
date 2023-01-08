package com.pvrschcms.pvrcinemaschdulernew.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pvrschcms.pvrcinemaschdulernew.model.response.LoginResponse;
import com.pvrschcms.pvrcinemaschdulernew.model.user.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.service.MyUserDetailsService;


@Controller
@RequestMapping("/web/admin/")
public class LoginWebController {
	@Autowired
    private MyUserDetailsService userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping("login")
	public String loginPage(Model model) {
	    return "admin-login";
	}
	
	@RequestMapping("login/dashboard")
	public ModelAndView loginRequest(HttpServletRequest request,HttpServletResponse response ,@RequestParam String username,@RequestParam String password,Model model) {
		ModelAndView andView =  new ModelAndView();
		try {
			if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
				LoginResponse resp = userService.authenticateUserWeb(username,password);
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				UserModel userDetails = null;
				userDetails = (UserModel) principal;
				System.out.println(userDetails.getUsername());
				//System.out.println(resp.getAccessToken()+" :: type :: "+resp.getTokenType());
				if(response!=null) {
					//model.addAttribute("loginResponse", resp);
				    //return "redirect:/web/admin/dashboard";
					//redirectStrategy.sendRedirect(request, response, "/web/admin/dashboard");
				    //response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/web/admin/dashboard"));
					
					andView.addObject("userDetails", userDetails);
					andView.setViewName("admin-dashboard");
					return andView;
				}else {
					//redirectStrategy.sendRedirect(request, response,"/web/admin/login");
					andView.setViewName("redirect:/web/admin/login");
					return andView;
				}
			}else {
				andView.setViewName("redirect:/web/admin/login");
				return andView;
			}
		}catch (Exception e) {
			System.out.println(" excption loginRequest() "+e.getMessage());
			andView.setViewName("redirect:/web/admin/login");
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