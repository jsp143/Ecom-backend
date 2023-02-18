package com.pvrschcms.pvrcinemaschdulernew.user.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	private String accessToken;
    private String tokenType = "Bearer";
    private String name;
    private String username;
    private String email;
    private String mobile;
	public LoginResponse() {
		super();
	}
	public LoginResponse(String accessToken, String name, String username, String email, String mobile) {
		super();
		this.accessToken = accessToken;
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
	}
	
    
}
