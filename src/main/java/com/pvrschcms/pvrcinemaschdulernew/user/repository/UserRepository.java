package com.pvrschcms.pvrcinemaschdulernew.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findById(String id);

}
