package com.pvrschcms.pvrcinemaschdulernew.constant;

public class Constant {
	
	public enum response {
		success, error, dialog, pending
	}
	
	public static final Integer RESP_SUCCESS = 10001;
	public static final Integer RESP_PENDING = 10002;
	public static final Integer RESP_NOT_DATA_FOUND = 10003;
	public static final Integer RESP_ALERT_DIALOG = 10004;
	public static final Integer RESP_ALERT_ERROR = 10005;
	public static final Integer RESP_ALERT_REFRESH = 10006;
	public static final Integer RESP_ALERT_LOGOUT = 10007;
	public static final Integer RESP_ALERT_SESSION_EXPIRE = 10008;
	public static final Integer RESP_ALERT_VALIDATION = 10009;
	public static final Integer RESP_ALERT_LOGIN_AGAIN = 10010;
	public static final Integer RESP_ALERT_OTP = 10011;
	public static final Integer RESP_ALERT_PAGE_NOT_FOUND = 10012;
	public static final Integer RESP_ALERT_ACCESS_DENIED = 10013;
	public static final Integer RESP_ALERT_UNAUTHORIZED = 10014;


	public enum platform {
		WEBSITE, ANDROID, IOS
	}
	
	public enum bookingstatus {
		UNPAID_BOOKED, INITIALISED, RESCHEDULED, PARTIAL, BOOKED, CANCELLED, BAD_TRANS, PURCHASED, PENDING, USER_CANCEL, CANCEL_COMPLETE, PREBOOK_BOOKED
	}
	
	public enum paymentstatus {
		UNPAID, PARTIAL_PAID, PAID, ROLLEDBACK, REDEEMED, FAILED, USER_CANCEL, CANCEL_COMPLETE, RESCHEDULED, REFUNDED
	}
	
	public enum TypeUser {
	    TYPE_COUSTOMER,TYPE_ADMINISTRATOR,TYPE_ADMIN
	}
	
	public enum RoleName {
	    ROLE_USER, ROLE_ADMINISTRATOR, ROLE_ADMIN
	}
	
    public static final Boolean RESPONSE_STATUS_SUCCESS = true;
    public static final Boolean RESPONSE_STATUS_ERROR = false;
    
    public static final String TOKEN = "bearer";

    public static final String UTC_TIMEZONE = "UTC";
    
    public static class Message {
		public static final String ERROR = "Something Went Wrong!";
		public static final String SUCCESS = "Success";
		public static final String USER_CREATED_SUCCESSFULY = "User Created Successfully!";
		public static final String USER_NAME_IS_ALREADY_USED = "Oops! Username is already used!";
		public static final String USER_LOGIN_SUCCESS = "User Login Success";
	}
    
    public static class PAYMENTMODES {
		public static final String UPI = "UPI";
		public static final String PAYTM = "PAYTM";
	}
}
