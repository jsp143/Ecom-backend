package com.pvrschcms.pvrcinemaschdulernew.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.utils.mapper.UserMapper;
import com.pvrschcms.pvrcinemaschdulernew.user.model.request.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserModelRepository;

@SuppressWarnings("rawtypes")
@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger("ws");
	@Autowired
	private UserModelRepository userRepository;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Utility utility;


	@Cacheable(cacheNames = { "userCache" })
	public List<UserModel> findAllCoustomer() {
		try {
			return userRepository.findAllByIsDeletedFalse();
		}catch (Exception e) {
			logger.error("FIND ALL USER PROCESS :: findAllCoustomer {} ",utility.error(e));
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
			logger.error("CREATE USER PROCESS :: createCustomer {} ",utility.error(e));
			return null;
		}
	}

	public UserModel findUserDetail(String id) {
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			logger.error("FIND USER PROCESS :: findUserDetail {} ",utility.error(e));
			return null;
		}
	}
	
	public UserModel findmyDetail(String token) {
		try {
			return userRepository.findById(jwtTokenProvider.getUserIdFromJWT(token));
		} catch (Exception e) {
			logger.error("FIND MY USER PROCESS :: findmyDetail {} ",utility.error(e));
			return null;
		}
	}
}
