package com.pvrschcms.pvrcinemaschdulernew.utils.constant;

import com.pvrschcms.pvrcinemaschdulernew.user.model.request.LoginRequest;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {
    public boolean validateUserRequest(SignUpRequest request) {
        boolean res;
        if(request!=null && !request.getFirstName().isEmpty() && !request.getMobile().isEmpty()
                && !request.getEmail().isEmpty())
            res = true;
        else
            res = false;
        return res;
    }

    public boolean validateLoginRequest(LoginRequest loginRequest) {
        boolean res;
        if(loginRequest!=null && !loginRequest.getUsername().isEmpty() && !loginRequest.getPassword().isEmpty())
            res = true;
        else
            res = false;
        return res;
    }

    public boolean validateWebLoginRequest(String username, String password) {
        boolean res;
        if(!username.isEmpty() && !password.isEmpty())
            res = true;
        else
            res = false;
        return res;
    }
}
