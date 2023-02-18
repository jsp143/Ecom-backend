package com.pvrschcms.pvrcinemaschdulernew.user.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public RoleModel() {
		super();
	}

	public RoleModel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleModel(Integer id) {
		super();
		this.id = id;
	}
}
