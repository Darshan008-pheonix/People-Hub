package com.peoplehub.security.serviceImpln;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.peoplehub.security.config.CreateUserDetailsService;
import com.peoplehub.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Filter extends OncePerRequestFilter{

	@Autowired
	JwtService jwtService;
	@Autowired
	CreateUserDetailsService createUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=null,username=null;
		String authHeader=request.getHeader("Authorization");
		if(authHeader!=null) {
			token=authHeader.split(" ")[1];
			username=jwtService.extractUserName(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=createUserDetailsService.loadUserByUsername(username);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
