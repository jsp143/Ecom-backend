package com.pvrschcms.pvrcinemaschdulernew.user.controller.web;

import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.user.model.response.UserResponse;
import com.pvrschcms.pvrcinemaschdulernew.user.service.UserService;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ValidationUtils;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web/admin/user/")
public class UserWebController {
    Logger logger = LoggerFactory.getLogger("ws");
    @Autowired
    private UserService usrservice;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ValidationUtils validationUtils;

    //@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping(value = "create/user")
    public ModelAndView signUpUser(HttpServletRequest request, HttpServletResponse response ,Model model,final RedirectAttributes redirectAttributes,@RequestBody SignUpRequest signUpRequest) {
        logger.info("CREATE ADMIN USER PROCESS");
        ModelAndView andView =  new ModelAndView();
        try {
            if(validationUtils.validateUserRequest(signUpRequest)) {
                UserModel userModel = usrservice.createCustomer(signUpRequest);
                if (userModel != null) {
                    UserResponse userResponse = new UserResponse();
                    BeanUtils.copyProperties(userModel, userResponse);
                    redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, false);
                    redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.USER_CREATED_SUCCESSFULY);
                    andView.addObject(Constant.ATTRIBUTE.USER_RESPONSE, userResponse);
                    andView.setViewName(Constant.Redirect.USER_LIST);
                    return andView;
                } else {
                    redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
                    redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.ERROR);
                    andView.setViewName(Constant.Redirect.USER_LIST);
                    return andView;
                }
            }else{
                redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
                redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.USER_CREATE_REQUEST_INVALID);
                andView.setViewName(Constant.Redirect.USER_LIST);
                return andView;
            }
        }catch (Exception e) {
            logger.error("CREATE ADMIN USER PROCESS :: EXCEPTION {} ",e.getMessage());
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.ERROR);
            andView.setViewName(Constant.Redirect.USER_LIST);
            return andView;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "customer/list")
    public ModelAndView getAllCoustomer(HttpServletRequest request, HttpServletResponse response ,Model model,final RedirectAttributes redirectAttributes) {
        logger.debug("GET ALL CUSTOMER PROCCESS");
        ModelAndView andView =  new ModelAndView();
        try {
            List<UserModel> userlist = usrservice.findAllCoustomer();
            List<UserResponse> userResponses = new ArrayList<>();
            BeanUtils.copyProperties(userlist, userResponses);
            andView.addObject("userlist", userlist);
            andView.setViewName(Constant.Redirect.USER_LIST);
            return andView;
        }catch (Exception e) {
            logger.error("GET ALL CUSTOMER PROCCESS :: EXCEPTION {} ",e.getMessage());
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.ERROR);
            andView.setViewName(Constant.Redirect.DASHBOARD);
            return andView;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping(value = "my/detail")
    public ModelAndView getmyDetail(HttpServletRequest request, HttpServletResponse response ,Model model,final RedirectAttributes redirectAttributes) {
        logger.debug("GET MY DETAIL PROCCESS");
        ModelAndView andView =  new ModelAndView();
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserModel userDetails = (UserModel) principal;
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userDetails, userResponse);
            andView.addObject(Constant.ATTRIBUTE.USER_RESPONSE, userResponse);
            andView.setViewName("user-detail");
            return andView;
        } catch (Exception e) {
            logger.error("CUSTOMER DETAIL :: EXCEPTION {} ",e.getMessage());
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.ERROR);
            andView.setViewName(Constant.Redirect.DASHBOARD);
            return andView;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "user/detail")
    public ModelAndView getUserDetail(HttpServletRequest request, HttpServletResponse response ,Model model,final RedirectAttributes redirectAttributes,@RequestParam String id) {
        logger.debug("GET USER DETAIL by id {}",id);
        ModelAndView andView =  new ModelAndView();
        try {
            UserModel user = usrservice.findUserDetail(id);
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            andView.addObject(Constant.ATTRIBUTE.USER_RESPONSE, userResponse);
            andView.setViewName("user-detail");
            return andView;
        } catch (Exception e) {
            logger.error("OTHER CUSTOMER DETAIL :: EXCEPTION {} ",e.getMessage());
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_ERROR, true);
            redirectAttributes.addFlashAttribute(Constant.ATTRIBUTE.REDIRECT_FLASH_RESP, Constant.Message.ERROR);
            andView.setViewName(Constant.Redirect.DASHBOARD);
            return andView;
        }
    }
}
