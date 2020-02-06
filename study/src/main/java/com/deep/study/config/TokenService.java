package com.deep.study.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenService {

	private static final String SECRET_KEY = "kjhk3k#2343@123@#Fbd";
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
	private static final String SCHEME_NAME = "Bearer ";
	
	public String generateWebToken(Object subject) {

		return SCHEME_NAME+Jwts.builder().setSubject(new Gson().toJson(subject))
				.setExpiration(null)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

	}

		
	public boolean validateToken(String token){
		boolean validToken = true;
		try{
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(stripSchema(token));
			claims.getBody();
			
		}catch (ExpiredJwtException | UnsupportedJwtException| MalformedJwtException | SignatureException | IllegalArgumentException e){
			validToken = false;
			logger.error("Exception while validating token",e);
		}
		
		return validToken;
	}

	private String stripSchema(String token) {
		if(null==token){
			return null;
		}
		if(token.indexOf(SCHEME_NAME)<0){
			return null;
		}
		
		return token.substring(token.indexOf(SCHEME_NAME)+SCHEME_NAME.length());
	}
	
}
