package com.pvrschcms.pvrcinemaschdulernew.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class SignUpRequest {
    private String firstName;
    private String lastName = "";
    private String email;
    private String mobile;
    private String password;
    private String dob = "";
    private String countryCode = "";
    private String panNo = "";
    private String gender = "";
    private String aadharNo = "";
    private String roles;

    public SignUpRequest() {
    }

    public SignUpRequest(String firstName, String lastName, String email, String mobile, String password, String dob, String countryCode, String panNo, String gender, String aadharNo, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.dob = dob;
        this.countryCode = countryCode;
        this.panNo = panNo;
        this.gender = gender;
        this.aadharNo = aadharNo;
        this.roles = roles;
    }
}
