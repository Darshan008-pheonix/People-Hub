package com.peoplehub.security.serviceImpln;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.peoplehub.security.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpln implements JwtService{

	@Override
	public String generateToken(String username) {
		System.out.println("Claimmmmmsss");
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		System.out.println("Build....!!!!");
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30)).signWith(getKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getKey() {
		System.out.println("Keyyyyyyyy");
		String secrete="U8jQmH7NFqvVlSuPBv5i3kxBVD1TPGNz";
		byte[] keyBytes=Decoders.BASE64.decode("B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF");
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpDate(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token,Function<Claims ,T> claimResponse) {
		final Claims claims=extractAllClaims(token);
		return claimResponse.apply(claims);
	}
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	@Override
	public boolean isTokenExperied(String token) {
		return extractExpDate(token).after(new Date());
	}

	@Override
	public boolean validateToken(String token,UserDetails details) {
		
		return extractUserName(token).equals(details.getUsername())&&!isTokenExperied(token);
	}
	
	

	
}
