package com.blogjava.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

	@NotBlank
	@Size(min = 3, max = 50)
	private String userName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@JsonIgnore
	@NotBlank
	@Size(min = 0, max = 100)
	private String passwod;
	@Lob
	private String avatar;

	// Tao quan he
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles = new HashSet<>();

	public User() {
		
	}
	public User(Long id, @NotBlank @Size(min = 3, max = 50) String name,
			@NotBlank @Size(min = 3, max = 50) String userName, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 0, max = 100) String passwod, String avatar, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.passwod = passwod;
		this.avatar = avatar;
		this.roles = roles;
	}

	public User(@NotBlank @Size(min = 3, max = 50) String name, 
			@NotBlank @Size(min = 3, max = 50) String username,
			@NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 0, max = 100) String encode) {
		this.name = name;
		this.userName = username;
		this.email = email;
		this.passwod = encode;
	}

}
