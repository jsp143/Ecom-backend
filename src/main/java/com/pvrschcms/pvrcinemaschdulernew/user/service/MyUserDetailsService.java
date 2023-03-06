package com.pvrschcms.pvrcinemaschdulernew.user.service;

import javax.transaction.Transactional;

import com.pvrschcms.pvrcinemaschdulernew.user.model.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pvrschcms.pvrcinemaschdulernew.config.JwtTokenProvider;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.user.model.response.LoginResponse;
import com.pvrschcms.pvrcinemaschdulernew.user.model.User;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.user.model.UserPrincipal;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserModelRepository;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserPrincipalRepository;
import com.pvrschcms.pvrcinemaschdulernew.user.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger("ws");
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserPrincipalRepository userPrincipalRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserModelRepository userModelRepository;
 
	@Autowired
	private Utility utility;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
    	User user = userRepository.findByUsername(username);
    			 if (user == null) {
    		            throw new UsernameNotFoundException(username);
    		        }
    		    	System.out.println("user roles: " + new Gson().toJson(user).toString());

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id);
        if (user == null) {
        	  throw new UsernameNotFoundException("user not found with"+id);
        }
    	System.out.println("user roles: " + user);

        return UserPrincipal.create(user);
    }

	@SuppressWarnings("rawtypes")
	public ResponseDto authenticateUser(LoginRequest loginRequest) {
		ResponseDto response = new ResponseDto();
		try {
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
			
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = tokenProvider.generateToken(authentication);
	        UserModel um = userModelRepository.findById(jwtTokenProvider.getUserIdFromJWT(jwt));
	        LoginResponse ls = new LoginResponse(jwt,um.getName(),um.getUsername(),um.getEmailId(),um.getMobile());
	        response.setData(ls);
	        response.setCode(Constant.RESP_SUCCESS);
	        response.setMessage(Constant.Message.USER_LOGIN_SUCCESS);
	        response.setSuccess(true);
		}catch (Exception e) {
			e.printStackTrace();
			response.setCode(Constant.RESP_ALERT_ERROR);
	        response.setMessage(Constant.Message.ERROR);
	        response.setSuccess(false);
		}
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public LoginResponse authenticateUserWeb(String username, String password) {
		logger.debug("LOGIN PROCCESS authenticateUserWeb username:: "+username+" :: password :: "+password);
		LoginResponse ls = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		username,
	                		password
	                )
	        );
			
	        //SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = tokenProvider.generateToken(authentication);
			logger.debug("LOGIN PROCCESS authenticateUserWeb jwt:: "+jwt);
	        UserModel um = userModelRepository.findById(jwtTokenProvider.getUserIdFromJWT(jwt));
			logger.debug("LOGIN PROCCESS authenticateUserWeb getName:: "+um.getName());
	        ls = new LoginResponse(jwt,um.getName(),um.getUsername(),um.getEmailId(),um.getMobile());
	        UsernamePasswordAuthenticationToken authentication1 =new UsernamePasswordAuthenticationToken(um, null, authentication.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
}
