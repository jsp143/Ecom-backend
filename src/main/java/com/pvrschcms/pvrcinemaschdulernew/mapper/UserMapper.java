package com.pvrschcms.pvrcinemaschdulernew.mapper;

import com.pvrschcms.pvrcinemaschdulernew.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.model.request.SignUpRequest;
import com.pvrschcms.pvrcinemaschdulernew.model.user.RoleModel;
import com.pvrschcms.pvrcinemaschdulernew.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Component
public class UserMapper {
    @Autowired
    private Utility utility;
    @Autowired
    private RollMapper rollMapper;

    public UserModel mapusertomodel(SignUpRequest request) {

        Date dobtodate= utility.string_dd_MM_yyyy_HH_mm_ss_todate(request.getDob());
        UserModel userModel = new UserModel(UUID.randomUUID().toString(),request.getFirstName(), request.getLastName() ,request.getFirstName()+" "+request.getLastName(),request.getMobile(), request.getEmail(), request.getMobile(), request.getCountryCode(), BCrypt.hashpw(request.getPassword().trim(), BCrypt.gensalt()) , request.getGender(), Constant.TypeUser.TYPE_COUSTOMER.toString(), Constant.RoleName.ROLE_USER.toString(), true, false,dobtodate,request.getPanNo(),request.getAadharNo());
        Set<RoleModel> role = rollMapper.getRollCreateCustomer(request.getRoles());
        userModel.setRoles(role);
        return userModel;
    }

}
