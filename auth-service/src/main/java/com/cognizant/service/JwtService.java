package com.cognizant.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static final String SECRET = "u8f9d8s7f6g5h4j3k2l1m0n9b8v7c6x5u8f9d8s7f6g5h4j3"; // 32+ chars, Base64-encoded

	
	 public void validateToken(final String token) {
		 Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	 }
	 
	 public String generateToken(String username) {
		 Map<String, Object> claims = new HashMap<>();
		 return createToken(claims,username);
	 }
	 
	 public String createToken(Map<String, Object>claims, String username) {
		 return Jwts.builder()
				 .setClaims(claims)
				 .setSubject(username)
				 .setIssuedAt(new Date(System.currentTimeMillis()))
				 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				 .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	 }
	 
	 private Key getSignKey() {
		 byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		 return Keys.hmacShaKeyFor(keyBytes);
	 }
}
