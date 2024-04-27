package com.peoplehub.service;

import com.peoplehub.entity.Employee;

public interface HubService {

	String Login(String value1,String pswd);
	Employee findEmpByEid(String eid);
	
	boolean validateOtp(String eid,Long otp);
	
}
