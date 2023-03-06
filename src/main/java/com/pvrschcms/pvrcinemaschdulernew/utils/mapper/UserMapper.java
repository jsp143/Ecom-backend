package com.pvrschcms.pvrcinemaschdulernew.utils.mapper;

import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Component
public class UserMapper {
    Logger logger = LoggerFactory.getLogger("ws");
    @Autowired
    private Utility utility;
    @Autowired
    private RollMapper rollMapper;

    public UserModel mapusertomodel(SignUpRequest request) {
        try {
            Date dobtodate = null;
            if (!request.getDob().isEmpty())
                dobtodate= Utility.string_dd_MM_yyyy_HH_mm_ss_todate(request.getDob());

            UserModel userModel = new UserModel(UUID.randomUUID().toString(), request.getFirstName(), request.getLastName()
                    , request.getFirstName() + " " + request.getLastName(), request.getMobile(), request.getEmail()
                    , request.getMobile(), request.getCountryCode(), BCrypt.hashpw(request.getPassword().trim(), BCrypt.gensalt())
                    , request.getGender(), request.getType(), request.getRoleName(), true, false, dobtodate, request.getPanNo(), request.getAadharNo());
            Set<RoleModel> role = rollMapper.getRollCreateCustomer(request.getRoles());
            userModel.setRoles(role);
            return userModel;
        } catch (Exception e) {
            logger.debug("CREATE USER PROCESS MAP USER :: EXCEPTION {}", utility.error(e));
            return null;
        }
    }
}
