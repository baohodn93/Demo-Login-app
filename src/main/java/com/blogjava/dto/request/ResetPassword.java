package com.blogjava.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {

	private String email;
	private String password;
	
	public ResetPassword() {
		
	}
	public ResetPassword(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
}
