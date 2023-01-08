package com.pvrschcms.pvrcinemaschdulernew.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ResponseDto<T, E> {
    protected boolean success;
    protected String message;
    protected Integer code;
    protected T data;
    protected E error;
    @JsonIgnore protected T dbObj;
    protected Date timeStamp = new Date();

    public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	public ResponseDto(boolean success, String message, Integer code) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
	}


	public ResponseDto(boolean success, String message, Integer code, T data, E error) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
		this.error = error;
	}


	public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public E getError() {
        return error;
    }

    public void setError(E error) {
        this.error = error;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getDbObj() {
        return dbObj;
    }

    public void setDbObj(T dbObj) {
        this.dbObj = dbObj;
    }
}
