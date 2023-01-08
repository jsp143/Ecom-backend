package com.pvrschcms.pvrcinemaschdulernew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.model.user.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

	List<UserModel> findAllByIsDeletedFalse();

	UserModel findById(String id);

}
