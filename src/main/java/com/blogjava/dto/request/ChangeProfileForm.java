package com.blogjava.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeProfileForm {

	private String name;
	private String username;
	private String email;
	
	public ChangeProfileForm() {
		
	}
	public ChangeProfileForm(String name, String username, String email) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
	}
}
