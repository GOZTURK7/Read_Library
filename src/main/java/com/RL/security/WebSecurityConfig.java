package com.RL.security;

import com.RL.security.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	private  UserDetailsService userDetailsService;

	private static final String [] AUTH_WHITE_LIST= {
			"/v3/api-docs/**",
			"swagger-ui.html",
			"/swagger-ui/**",
			"/",
			"index.html",
			"/images/**"
	};

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITE_LIST);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().
				disable().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
				authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().and().
		authorizeRequests().antMatchers("/contact/**","/register","/signin","/actuator/**").permitAll().
		anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean 
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

}