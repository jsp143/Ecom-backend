package com.pvrschcms.pvrcinemaschdulernew.product.model.view;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SubDeptViewModel {
	@Id
	private String id;
	
	private String deptName;
	
	private String name;
	
	private String code;
	
	private String deptId;
	
	private String fileName="";
	
	private Boolean active=true;

	public SubDeptViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubDeptViewModel(String id, String deptName, String name, String code, String deptId, String fileName, Boolean active) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.name = name;
		this.code = code;
		this.deptId = deptId;
		this.fileName = fileName;
		this.active = active;
	}
	
}
