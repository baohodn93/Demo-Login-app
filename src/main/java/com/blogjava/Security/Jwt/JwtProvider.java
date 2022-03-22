package com.blogjava.Security.Jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.blogjava.Security.Userprincal.UserPrinciple;

@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	private String jwtSecrect = "baohodn93@gmail.com";
	private int jwtExpiration = 86400;// Thoi gian song cua token

	public String createToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecrect).compact();
	}

	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message:{}", e);
		} catch (MalformedJwtException e) {
			logger.error("Invalid format token -> Message:{}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Exprired JWT token -> Message:{}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token -> Message:{}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty -> Message:{}", e);
		}
		return false;
	}

	public String getUserNameFromToken(String token) {
		String userName = Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token).getBody().getSubject();
		return userName;
	}

}
