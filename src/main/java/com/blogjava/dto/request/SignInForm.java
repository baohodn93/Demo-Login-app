package com.blogjava.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInForm {

	private String username;
	private String password;

	public SignInForm() {

	}

	public SignInForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
