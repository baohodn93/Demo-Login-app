package com.blogjava.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

	private Long id;
	private String token;
	private String typeString = "Bearer";
	private String name;
	private Collection<? extends GrantedAuthority> roles;

	public JwtResponse() {
		
	}
	
	public JwtResponse(Long id, String token, String typeString, String name,
			Collection<? extends GrantedAuthority> roles) {
		this.id = id;
		this.token = token;
		this.typeString = typeString;
		this.name = name;
		this.roles = roles;
	}

	public JwtResponse(Long id, String token, String name, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.token = token;
		this.name = name;
		this.roles = authorities;
	}

}
