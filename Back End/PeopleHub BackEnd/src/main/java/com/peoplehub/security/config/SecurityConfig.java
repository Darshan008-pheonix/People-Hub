package com.peoplehub.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.peoplehub.security.serviceImpln.Filter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
		@Autowired
		Filter filter;
	
		@Bean
		public UserDetailsService userDetailsService() {
			return new CreateUserDetailsService();
		}
	
		
		
		
		
		@Bean
		public AuthenticationManager authObj(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}
		
		@Bean
		public SecurityFilterChain authorizationConfig(HttpSecurity http)throws Exception{
			http.csrf((csrf)->csrf.disable()).authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.
					requestMatchers("/loginpage","/auth").permitAll()).sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider()).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
			return http.build();
		}
		
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
	    public DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	       
	        authenticationProvider.setUserDetailsService(userDetailsService());
	       
	        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
	        return authenticationProvider;
	    }
	
}
