package com.peoplehub.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.peoplehub.entity.Employee;
import com.peoplehub.repo.EmployeeRepo;

@Component
public class CreateUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub


	
		Employee e=repo.findEmpByEidOrEmail(username, username);
		
		if(e==null) {
			throw new UsernameNotFoundException("Given Credentials Are Wrong");
		}
		else {
		
		    UserDetailsSetter u = new UserDetailsSetter(e);

		    return u;
		}
	}
}
