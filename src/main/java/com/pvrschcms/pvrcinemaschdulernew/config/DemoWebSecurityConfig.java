package com.pvrschcms.pvrcinemaschdulernew.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pvrschcms.pvrcinemaschdulernew.utils.exception.CustomAuthFailureResponse;
import com.pvrschcms.pvrcinemaschdulernew.user.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)

public class DemoWebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	 	@Autowired
	    MyUserDetailsService customUserDetailsService;

	    @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;
	    @Autowired CustomAuthFailureResponse authenticationExceptionHandler;
	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	    }
	    
	    
	    
	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	    }

	    @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    private static final String[] SPECIAL_WHITELIST = {
	            "/swagger/ui/**",
				"/v2/api-docs",
				"/swagger-resources/**",
				"/swagger/ui/**",
				"/swagger-ui.html",
				"/swagger-ui/",
				"/api/web/user/create/user",
				"/api/web/user/signup/customer",

	    };
	    
	    private static final String[] AUTH_WHITELIST = {
	            "/webjars/**",
	            "/assets/**",
	            "/api/web/auth/signin",
	            "/web/admin/login",
				"/web/admin/authenticate",
				"/web/admin/dashboard"
	    };
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .exceptionHandling()
	                    .authenticationEntryPoint(unauthorizedHandler)
	                    .and()
	                .sessionManagement()
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                    .and()
	                .authorizeRequests()
	                    .antMatchers("/","/favicon.ico","/**/*.png","/**/*.gif","/**/*.svg","/**/*.jpg","/**/*.html","/**/*.css","/**/*.js")
	                        .permitAll()
	                    .antMatchers(AUTH_WHITELIST)
	                    	.permitAll()
	                    .antMatchers(SPECIAL_WHITELIST)
	                    	.permitAll()
	                    .antMatchers("/web/admin/**")
	                    	.hasAnyRole("ROLE_ADMINISTRATOR","ROLE_ADMIN")
	                    .anyRequest()
	                        .authenticated();
	        
	        http.exceptionHandling()
            .authenticationEntryPoint(authenticationExceptionHandler);
	        

	        // Add our custom JWT security filter
	        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    }
		
	
}
