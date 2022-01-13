package com.systempro.crud.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final JwtTokenProvaider jwtTokenProvaider;

	@Autowired
	public JwtConfigure(JwtTokenProvaider jwtTokenProvaider) {
		this.jwtTokenProvaider = jwtTokenProvaider;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {		
		JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvaider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);		
	}
}
