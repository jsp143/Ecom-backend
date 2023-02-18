package com.pvrschcms.pvrcinemaschdulernew.user.model.response;

import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
public class UserResponse {
    private String name;
    private String mobile;
    private String emailId;
    private Date dob;
    private String panNo;
    private String aadharNo;
    private Set<RoleModel> roles;
}
