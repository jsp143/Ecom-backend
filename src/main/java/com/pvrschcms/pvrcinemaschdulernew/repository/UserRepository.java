package com.pvrschcms.pvrcinemaschdulernew.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findById(String id);

}
