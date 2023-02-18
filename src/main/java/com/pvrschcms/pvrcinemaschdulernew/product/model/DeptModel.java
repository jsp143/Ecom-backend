package com.pvrschcms.pvrcinemaschdulernew.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pvrschcms.pvrcinemaschdulernew.audit.DateAuditView;

import lombok.Getter;
import lombok.Setter;

//@ConfigurationProperties(prefix = "file")
@Table(name = "sys_department")
@Entity
@Getter
@Setter
public class DeptModel extends DateAuditView {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String name;
	
	private String code;
	
	private String fileName="";
	
	@JsonIgnore
	private String contentType="";
	
	@JsonIgnore
	private String fileSize="";
	
	@JsonIgnore
	private String uploadDir="";
	
	@JsonIgnore
	private Boolean active=true;

	public DeptModel() {
		super();
	}

	public DeptModel(String id, String name, String code, String fileName, String contentType, String fileSize,String uploadDir) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.uploadDir = uploadDir;
	}

	public DeptModel(String id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

}
