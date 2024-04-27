package com.peoplehub.serviceimpln;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.peoplehub.entity.Employee;
import com.peoplehub.entity.Otp;
import com.peoplehub.exception.InvalidCredentailsException;
import com.peoplehub.exception.InvalidOtpException;
import com.peoplehub.repo.EmployeeRepo;
import com.peoplehub.repo.OtpRepo;
import com.peoplehub.service.HubService;

@Service
public class HubServiceImpln implements HubService{
    
	@Autowired
	EmployeeRepo eRepo;
	
	@Autowired
	OtpRepo oRepo;
	
	@Autowired
    JavaMailSender mail;
	
	BCryptPasswordEncoder crypt=new BCryptPasswordEncoder();
	
	@Override
	public String Login(String value1, String pswd) {
		// TODO Auto-generated method stub
		Employee e=eRepo.findEmpByEidOrEmail(value1, value1);
		if(e!=null && crypt.matches(pswd, e.getPassword())) {
			 generateOtp(e.getEid(),e.getEname(),e.getEmail());
			return e.getEid();
		}
		else {
			throw new InvalidCredentailsException();
		}
	}

	public String  generateOtp(String eid,String ename,String email) {
		
		SimpleMailMessage s=new SimpleMailMessage();
		int otp=(int)(Math.random()*100000);
		oRepo.save(new Otp(eid,otp));
		System.out.println(otp);
		s.setFrom("noreply.peoplehub@gmail.com");
		s.setTo(email);
		s.setSubject("Verification Otp");
		s.setText("Hi "+ename+",\n Please find OTP for people hub login OTP:"+otp+".\n If you are insist for OTP please do contact support team");
		mail.send(s);
		System.out.println("Send...!!!!");
		return "OTP SENT";
	}

	@Override
	public Employee findEmpByEid(String eid) {
		return eRepo.findById(eid).orElseThrow(()->new InvalidCredentailsException());
	}

	@Override
	public boolean validateOtp(String eid,Long otp) {
		Optional<Otp> obj=oRepo.findById(eid);
		if(obj.isEmpty()) {
			throw new UsernameNotFoundException("user not found");
		}
		else {
			Otp o1=obj.get();
			if(o1.getNo()==otp) {
				oRepo.deleteById(eid);
				return true;
			}
			else {
				throw new InvalidOtpException();
			}
		}
	}
}
