package com.pvrschcms.pvrcinemaschdulernew.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {

	List<UserModel> findAllByIsDeletedFalse();

	UserModel findById(String id);

}
