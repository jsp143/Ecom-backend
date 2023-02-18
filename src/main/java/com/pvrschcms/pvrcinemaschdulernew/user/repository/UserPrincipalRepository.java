package com.pvrschcms.pvrcinemaschdulernew.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvrschcms.pvrcinemaschdulernew.user.model.User;

public interface UserPrincipalRepository extends JpaRepository<User, String>{

	User findByUsername(String username);

	User findById(Long id);

	User findByEmail(String email);

	User findByMobile(String mobile);


}
