package com.pvrschcms.pvrcinemaschdulernew.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "sys_subdept")
@Entity
@Getter
@Setter
public class SubDeptModel {
	@Id
	private String id;
	
	private String name;
	
	private String code;
	
	private String deptId;
	
	private String fileName="";
	
	private String contentType="";
	
	private String fileSize="";
	
	private String uploadDir="";
	
	private Boolean active=true;

	public SubDeptModel(String id, String name, String code, String deptId, String fileName, String contentType,
			String fileSize, String uploadDir) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.deptId = deptId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.uploadDir = uploadDir;
	}

	public SubDeptModel(String id, String name, String code, String deptId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.deptId = deptId;
	}

	public SubDeptModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
