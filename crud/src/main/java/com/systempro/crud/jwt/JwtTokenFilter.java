package com.systempro.crud.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvaider jwtTokenProvaider;

	@Autowired
	public JwtTokenFilter(JwtTokenProvaider jwtTokenProvaider) {
		this.jwtTokenProvaider = jwtTokenProvaider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = jwtTokenProvaider.resolveToken((HttpServletRequest) request);
		if (token != null && jwtTokenProvaider.validateToken(token)) {
			Authentication auth = jwtTokenProvaider.getAuthentication(token);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

}
