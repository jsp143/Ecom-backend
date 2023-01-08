package com.pvrschcms.pvrcinemaschdulernew.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvrschcms.pvrcinemaschdulernew.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.model.user.User;
import com.pvrschcms.pvrcinemaschdulernew.service.MyUserDetailsService;

@RestController
@RequestMapping("/web/admin/auth/")

public class AuthController {
	
    
    @Autowired
    private MyUserDetailsService userService;

    @SuppressWarnings("rawtypes")
	@PostMapping("signin")
    public ResponseDto authenticateUser(@Validated @RequestBody User loginRequest) {
        return userService.authenticateUser(loginRequest);
    }
    
    
}

