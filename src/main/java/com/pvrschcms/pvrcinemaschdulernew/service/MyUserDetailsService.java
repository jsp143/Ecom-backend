package com.pvrschcms.pvrcinemaschdulernew.service;

import javax.transaction.Transactional;

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
import com.pvrschcms.pvrcinemaschdulernew.constant.Constant;
import com.pvrschcms.pvrcinemaschdulernew.constant.ResponseDto;
import com.pvrschcms.pvrcinemaschdulernew.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.model.response.LoginResponse;
import com.pvrschcms.pvrcinemaschdulernew.model.user.User;
import com.pvrschcms.pvrcinemaschdulernew.model.user.UserModel;
import com.pvrschcms.pvrcinemaschdulernew.model.user.UserPrincipal;
import com.pvrschcms.pvrcinemaschdulernew.repository.UserModelRepository;
import com.pvrschcms.pvrcinemaschdulernew.repository.UserPrincipalRepository;
import com.pvrschcms.pvrcinemaschdulernew.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
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
	public ResponseDto authenticateUser(User loginRequest) {
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
	        UserModel um = userModelRepository.findById(jwtTokenProvider.getUserIdFromJWT(jwt));
	        ls = new LoginResponse(jwt,um.getName(),um.getUsername(),um.getEmailId(),um.getMobile());
	        UsernamePasswordAuthenticationToken authentication1 =new UsernamePasswordAuthenticationToken(um, null, authentication.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
}
