package com.peoplehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.peoplehub.entity.Employee;
import com.peoplehub.exception.InvalidCredentailsException;
import com.peoplehub.exception.InvalidOtpException;
import com.peoplehub.security.service.JwtService;
import com.peoplehub.service.HubService;

@RestController
public class HubController {

	@Autowired
	HubService hubService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	
	BCryptPasswordEncoder b=new BCryptPasswordEncoder();
	
	@GetMapping("loginpage")
	ResponseEntity Login(@RequestHeader String value,@RequestHeader String password) {
		return new ResponseEntity<>(hubService.Login(value, password),HttpStatus.OK);
	}
	
	@GetMapping("auth")
	ResponseEntity auth(@RequestHeader String eid,@RequestHeader String pswd,@RequestHeader Long otp) {
		
		if(hubService.validateOtp(eid, otp)){
			Authentication tok=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(eid,pswd));
			if(tok.isAuthenticated()) {
				return new ResponseEntity<> (jwtService.generateToken(eid),HttpStatus.OK);
			}
			else {
				throw new UsernameNotFoundException("Token Generation Failed...!");
			}
		}
		else {
			throw new InvalidOtpException();
		}
	}
	
}
