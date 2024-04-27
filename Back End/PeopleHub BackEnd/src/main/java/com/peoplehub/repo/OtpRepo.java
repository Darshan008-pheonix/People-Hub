package com.peoplehub.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peoplehub.entity.Otp;

public interface OtpRepo  extends JpaRepository<Otp, String>{

}
