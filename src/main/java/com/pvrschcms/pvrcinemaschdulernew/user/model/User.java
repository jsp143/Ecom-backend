package com.pvrschcms.pvrcinemaschdulernew.user.model;

import javax.persistence.*;

import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String mobile;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles;

    public User() {

    }

	public User(String id, String name, String username, String email, String mobile, String password,
			Set<RoleModel> roles) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.roles = roles;
	}
}
