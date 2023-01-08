package com.pvrschcms.pvrcinemaschdulernew.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.model.user.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

	RoleModel findByName(String role);

}
