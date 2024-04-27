package com.peoplehub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peoplehub.entity.Employee;

public interface EmployeeRepo  extends JpaRepository<Employee, String>{
	
	@Query(nativeQuery=true,value="select * from employee where eid =?1 or email=?2")
	Employee findEmpByEidOrEmail(String value1,String value2);

}
