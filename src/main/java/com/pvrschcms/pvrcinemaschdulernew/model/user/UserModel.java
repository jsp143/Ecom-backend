package com.pvrschcms.pvrcinemaschdulernew.model.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pvrschcms.pvrcinemaschdulernew.model.audit.DateAudit;

import lombok.Getter;
import lombok.Setter;

@Table(name = "user")
@Entity
@Getter
@Setter
public class UserModel extends DateAudit {
	private static final long serialVersionUID = 1L;
	@Id
    private String id;
	
    private String firstName;

    private String lastName;
    
    private String name;

    private String username;

    private String emailId;

    private String mobile;
    
    private String countryCode;

    private String password;
    
    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String type;

    @Column(length = 20)
    private String roleName;

    private Boolean isActive;

    private Boolean isDeleted;
    
    private Date dob;
    
    private String panNo;
    
    private String aadharNo;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles;


	public UserModel() {
		
	}


	public UserModel(String id, String firstName, String lastName, String name, String username, String emailId,
			String mobile, String countryCode, String password, String gender, String type, String roleName,
			Boolean isActive, Boolean isDeleted, Date dob, String panNo, String aadharNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
		this.username = username;
		this.emailId = emailId;
		this.mobile = mobile;
		this.countryCode = countryCode;
		this.password = password;
		this.gender = gender;
		this.type = type;
		this.roleName = roleName;
		this.isActive = isActive;
		this.isDeleted = isDeleted;
		this.dob = dob;
		this.panNo = panNo;
		this.aadharNo = aadharNo;
	}

   
}
