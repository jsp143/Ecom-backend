package com.pvrschcms.pvrcinemaschdulernew.user.controller;


import com.pvrschcms.pvrcinemaschdulernew.user.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.user.model.User;
import com.pvrschcms.pvrcinemaschdulernew.user.service.MyUserDetailsService;

@RestController
@RequestMapping("/web/admin/auth/")

public class AuthController {
	
    
    @Autowired
    private MyUserDetailsService userService;

    @SuppressWarnings("rawtypes")
	@PostMapping("signin")
    public ResponseDto authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }
    
    
}

