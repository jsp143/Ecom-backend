package com.pvrschcms.pvrcinemaschdulernew.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.user.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

	RoleModel findByName(String role);

}
