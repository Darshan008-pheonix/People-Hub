package com.peoplehub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private String eid; 
	private String ename;
	private String email;
	private String password;
	private String Roles;
	@OneToOne
	private BankAccount account;
	
	
}
