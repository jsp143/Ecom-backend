package com.pvrschcms.pvrcinemaschdulernew.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.pvrschcms.pvrcinemaschdulernew.utils.mapper.UserMapper;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserModelRepository;

@SuppressWarnings("rawtypes")
@Service
public class UserService {
	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;


	@Cacheable(cacheNames = { "userCache" })
	public List<UserModel> findAllCoustomer() {
		try {
			return userRepository.findAllByIsDeletedFalse();
		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	@Transactional
	public UserModel createCustomer(SignUpRequest request) {
		try {
			UserModel userModel = userMapper.mapusertomodel(request);
			UserModel userresp = null;
			if(userModel!=null){
				userresp = userRepository.save(userModel);
			}
			return userresp;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserModel findUserDetail(String id) {
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UserModel findmyDetail(String token) {
		try {
			return userRepository.findById(jwtTokenProvider.getUserIdFromJWT(token));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
